package com.xeehoo.p2p.controller;

import com.sun.jndi.toolkit.url.Uri;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.LoanSlider;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.service.LoanCacheService;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/16.
 */
@Controller
public class LoanInvestController {
    private final Logger logger = Logger.getLogger(LoanInvestController.class);

    @Autowired
    private LoanInvestService investService;

    @Autowired
    private LoanCacheService cacheService;

    @Autowired
    private LoanDictService dictService;


    /**
     * 个人投资产品
     *
     * @param request
     * @param amount  投资金额
     * @param productId  投资产品ID
     * @param sign 签名数据（未用）
     * @return
     */
    @RequestMapping(value = "/invest", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                               @RequestParam(value = "amt", required = true) Long amount,
                               @RequestParam(value = "product", required = true) Integer productId,
                               @RequestParam(value = "sign", required = false) String sign) {
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return CommonUtil.generateJsonMap("login", "没有登录");
        }

        try {
            Integer investId = investService.updateProductUserAmount(productId, so.getUserID(), so.getLoginName(), amount);
            if (investId > 0) {
                logger.info("investId is " + investId);
                return CommonUtil.generateJsonMap("OK", null);
            } else {
                return CommonUtil.generateJsonMap("failed", "投资失败");
            }
        } catch (Exception e){
            return CommonUtil.generateJsonMap("exception", "系统例外");
        }
    }

    @RequestMapping(value = "/queryInvestment", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView queryInvestment(HttpServletRequest request,
                                @RequestParam(value = "type", required = false) String productType,
                                @RequestParam(value = "rate", required = false) String loanRate,
                                @RequestParam(value = "stat", required = false) Integer productStatus,
                                @RequestParam(value = "page", required = true) Integer page) {
        Map<String, Object> cond = new HashMap<String, Object>();
        if (StringUtils.isEmpty(productType))
            productType = "0000";

        if (StringUtils.isEmpty(loanRate)){
            loanRate = "1-50";
        }

        if (StringUtils.isEmpty(productStatus))
            productStatus = 9;

        String[] rates = loanRate.split("-");
        cond.put("_minRate", Integer.parseInt(rates[0]));
        cond.put("_maxRate", Integer.parseInt(rates[1]));
        cond.put("rate", loanRate);
        cond.put("type", productType);
        cond.put("stat", productStatus);

        Integer pageSize = 5;
        Integer totalSize = investService.getTotalProduct(cond);
        List<LoanProduct> products = investService.getInvestProductPager(page, pageSize, cond);
        LoanPagedListHolder pagedListHolder = new LoanPagedListHolder();
        pagedListHolder.setSource(products);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setTotalSize(totalSize);
        pagedListHolder.setMaxLinkedPages(5);

        ModelAndView mav = new ModelAndView("/investment/invest");
        mav.addObject("pagedListHolder", pagedListHolder);

        // 获取产品类型
        List<LoanDict1>  dict1s = dictService.getAllDict1(Constant.DICT_PRODUCT_TYPE);
        Map<String, Object> ptypes = new HashMap<String, Object>();
        for (LoanDict1 dict1 : dict1s){
            ptypes.put(dict1.getDict1Code(),dict1.getDict1Name());
        }
        mav.addObject("productTypes", ptypes);

        UriUtils.createQueryCond(request, mav, cond);
        return mav;
    }


    @RequestMapping(value = "/product", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public HashMap<String, Object> getProduct(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam(value = "type", required = false) String productType,
                                              @RequestParam(value = "page", required = false) Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
//        HashMap<String, Object> cond = new HashMap<String, Object>();
        QueryCondition queryCondition = new QueryCondition();

        if (page == null || page <= 0)
            page = 1;

        queryCondition.put("type", productType);
        queryCondition.put("stat", Constant.PRODUCT_STATUS_RELEASE);
//        if (productType != null) {
//            cond.put("type", productType);
//        }
        Integer totalSize = investService.getTotalProduct(queryCondition.getCond());
        if (totalSize <= 0){
            map.put("totalSize", totalSize);
            return map;
        }
//        cond.remove("totalSize");

//        cond.put("pageSize", 10);
//        cond.put("offset", (page - 1) * 10);
//        cond.put("type", productType);
        List<LoanProduct> products = investService.getInvestProductPager(page - 1, 5, queryCondition.getCond());
        if (products != null && products.size() > 0){
            map.put("totalSize", totalSize);
            map.put("product", products);
        }

        return map;
    }

    @RequestMapping(value = "/cache/product", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<LoanProduct> cacheProduct(HttpServletRequest request, HttpServletResponse response){
//        String[] productTypes = {"1001", "1002", "1003"};

//        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> cond = new HashMap<String, Object>();
//        cond.put("pageSize", 6);
//        cond.put("offset", 0);
//        for (String type : productTypes){
//            cond.put("type", type);
            List<LoanProduct>  products = investService.getInvestProductPager(0, 6, cond);
//            if (products != null && products.size() > 0){
//                map.put("I" + type, products);
//            }
//        }

        return products;
    }

}
