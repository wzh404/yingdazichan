package com.xeehoo.p2p.controller;

import com.sun.jndi.toolkit.url.Uri;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.service.LoanCacheService;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.UriUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
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

    @RequestMapping(value = "/invest", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView invest(HttpServletRequest request,
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
        cond.put("minRate", Integer.parseInt(rates[0]));
        cond.put("maxRate", Integer.parseInt(rates[1]));
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
        List<LoanDict1>  dict1s = dictService.getAllDict1("10");
        Map<String, Object> ptypes = new HashMap<String, Object>();
        for (LoanDict1 dict1 : dict1s){
            ptypes.put(dict1.getDict1Code(),dict1.getDict1Name());
        }
        mav.addObject("productTypes", ptypes);

        createQueryCond(request, mav, cond);
        return mav;
    }

    private void createQueryCond(HttpServletRequest request, ModelAndView mav, Map<String, Object> cond){
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        // 删除page
        String pageUri = UriUtils.deleteLinkKey(queryString, "page");
        mav.addObject("pageUri", uri + pageUri);

        for (Map.Entry<String, Object> entry : cond.entrySet()){
            String condUri = UriUtils.deleteLinkKey(pageUri, entry.getKey());
            mav.addObject(entry.getKey(), entry.getValue());
            if (StringUtils.isEmpty(condUri)){
                mav.addObject(entry.getKey() + "Uri", uri + "?page=0");
            }
            else{
                mav.addObject(entry.getKey() + "Uri", uri + "?page=0" + (condUri.charAt(0) == '&' ? "" : "&") + condUri);
            }
        }
    }
}
