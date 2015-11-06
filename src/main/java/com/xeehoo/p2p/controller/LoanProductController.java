package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wangzunhui on 2015/11/4.
 */
@Controller
public class LoanProductController {
    @Autowired
    private LoanDictService dictService;

    @Autowired
    private LoanInvestService investService;

    @RequestMapping(value="/admin/product")
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
}
