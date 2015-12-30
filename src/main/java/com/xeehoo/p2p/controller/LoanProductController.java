package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.UriUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wangzunhui on 2015/11/4.
 */
@Controller
public class LoanProductController {
    private final Logger logger = Logger.getLogger(LoanProductController.class);

    @Autowired
    private LoanDictService dictService;

    @Autowired
    private LoanInvestService investService;

    @RequestMapping(value="/admin/product")
    @Permission("0201")
    public ModelAndView execute(HttpServletRequest request,
            @RequestParam(value = "type", required = false) String productType,
            @RequestParam(value = "stat", required = false) Integer productStatus,
            @RequestParam(value = "date", required = false) String productDate,
            @RequestParam(value = "low_rate", required = false) Integer lowRate,
            @RequestParam(value = "high_rate", required = false) Integer highRate,
            @RequestParam(value = "low_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date lowDate,
            @RequestParam(value = "high_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date highDate,
            @RequestParam(value = "page", required = false) Integer page){
        if (page == null) page = 0;

        QueryCondition queryCondition = new QueryCondition();
        queryCondition.put("type", productType);
        queryCondition.put("stat", productStatus);
        queryCondition.put("rate", lowRate, highRate);
        queryCondition.put("date", productDate, lowDate, highDate);

        LoanPagedListHolder pagedListHolder = investService.getProductPager(page, queryCondition.getCond());

        ModelAndView mav = new ModelAndView("/admin/list_product");
        dictService.setDict1NameAndCode(mav, "productTypes", Constant.DICT_PRODUCT_TYPE);
        mav.addObject("pagedListHolder", pagedListHolder);
        queryCondition.setModelAndView(request.getRequestURI(), mav);

        return mav;
    }

    @RequestMapping(value="/admin/releaseProduct")
    @Permission("0201")
    public ModelAndView releaseProduct(HttpServletRequest request,
                                @RequestParam(value = "product_id", required = false) Integer productId){
        List<LoanDict1> dict1s = dictService.getAllDict1(Constant.DICT_PRODUCT_TYPE);
        ModelAndView mav = new ModelAndView("/admin/edit_product");
        mav.addObject("productTypes", dict1s);

        if (productId != null && productId > 0){
            logger.info("productId is " + productId);

            LoanProduct product = investService.getProduct(productId);
            mav.addObject("product", product);
            List<Map<String, Object>> userInvestments = investService.getProductInvestments(productId);

            mav.addObject("investments", userInvestments);
        }
        else{
            LoanProduct product  = new LoanProduct();
            product.setMinAmount(new BigDecimal(100));
            product.setMaxAmount(new BigDecimal(0));
            mav.addObject("product", product);
        }

        return mav;
    }

    @RequestMapping(value="/admin/saveProduct")
    @Permission("0201")
    public ModelAndView saveProduct(@ModelAttribute("product")LoanProduct product){
        logger.info("product.name is " + product.getProductType());
        product.setStaffId(1);
        product.setReleaseTime(new Date());
        product.setProductStatus(1);
        product.setResidualAmount(product.getTotalAmount());
        investService.saveProduct(product);

        return new ModelAndView("redirect:/admin/product");
    }
}
