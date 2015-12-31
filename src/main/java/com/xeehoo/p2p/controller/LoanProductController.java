package com.xeehoo.p2p.controller;

import com.fuiou.data.QueryBalanceReqData;
import com.fuiou.data.QueryBalanceResultData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.service.FuiouService;
import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanDict1;
import com.xeehoo.p2p.po.LoanProduct;
import com.xeehoo.p2p.po.StaffSessionObject;
import com.xeehoo.p2p.service.LoanDictService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment environment;

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
            product.setRaiseDays(10);
            product.setMinAmount(new BigDecimal(100));
            product.setMinAddAmount(new BigDecimal(100));
            product.setMaxAmount(new BigDecimal(0));
            mav.addObject("product", product);
        }

        return mav;
    }

    @RequestMapping(value="/admin/saveProduct")
    @Permission("0201")
    public ModelAndView saveProduct(HttpServletRequest request, @ModelAttribute("product")LoanProduct product){
        logger.info("product.name is " + product.getProductType());
        StaffSessionObject sso = (StaffSessionObject)request.getSession().getAttribute("staff");
        product.setStaffId(sso.getStaffId());
        product.setReleaseTime(new Date());
        product.setProductStatus(1);
        product.setResidualAmount(product.getTotalAmount());
        investService.saveProduct(product);

        return new ModelAndView("redirect:/admin/product");
    }

    @RequestMapping(value="/admin/settleAccount")
    @Permission("0201")
    public ModelAndView settleAccount(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/settle_accounts");
        mav.addObject("balance", queryBalance("user114"));

        return mav;
    }

    /**
     * 查询用户账户余额
     *
     * @param mobile
     * @return
     */
    private Map<String, BigDecimal> queryBalance(String mobile){
        QueryBalanceReqData data = new QueryBalanceReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); //商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setCust_no(mobile);  // 用户ID
        data.setMchnt_txn_dt(CommonUtil.getCurrentDate()); //交易日期

        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        try {
            QueryBalanceRspData rsp = FuiouService.balanceAction(data);
            logger.info(rsp.toString());

            QueryBalanceResultData resultData = rsp.getResults().get(0);
            long ct = Long.parseLong(resultData.getCt_balance());
            long ca = Long.parseLong(resultData.getCa_balance());
            long cf = Long.parseLong(resultData.getCf_balance());
            long cu = Long.parseLong(resultData.getCu_balance());

            map.put("ct", new BigDecimal(ct / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP));
            map.put("ca", new BigDecimal(ca / 100.0).setScale(2,   BigDecimal.ROUND_HALF_UP));
            map.put("cf", new BigDecimal(cf / 100.0).setScale(2,   BigDecimal.ROUND_HALF_UP));
            map.put("cu", new BigDecimal(cu / 100.0).setScale(2,   BigDecimal.ROUND_HALF_UP));
        } catch (Exception e) {
            e.printStackTrace();
            // 异常，金额为0.0;
            map.put("ct", new BigDecimal(0.0));
            map.put("ca", new BigDecimal(0.0));
            map.put("cf", new BigDecimal(0.0));
            map.put("cu", new BigDecimal(0.0));
        }

        return map;
    }
}
