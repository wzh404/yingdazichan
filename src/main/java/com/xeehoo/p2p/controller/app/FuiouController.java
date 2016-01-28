package com.xeehoo.p2p.controller.app;

import com.fuiou.data.AppRegReqData;
import com.fuiou.data.AppTransReqData;
import com.fuiou.util.SecurityUtils;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.service.TokenService;
import com.xeehoo.p2p.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by wangzunhui on 2016/1/28.
 */
@Controller
public class FuiouController {
    private final Logger logger = Logger.getLogger(FuiouController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Environment environment;

    /**
     * 17. 页面注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/fuiou/register", method = RequestMethod.GET)
    public ModelAndView webReg(HttpServletRequest request,
                               @RequestParam(value = "token", required = true) String token){
        logger.info("token is " + token);
        String[] v = tokenService.getUserByToken(token);
        if (v == null){
            logger.error("token is invalidate.");
            return null;
        }
        logger.info("mobile is " + v[1]);

        AppRegReqData data = new AppRegReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setMobile_no(v[1]);  // 账号
        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/app/app_reg");
        mav.addObject("data", data);
        mav.addObject("action", "appWebReg");

        return mav;
    }

    /**
     * 商户P2P网站免登录网银充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/fuiou/recharge", method = RequestMethod.GET)
    public ModelAndView bankRecharge(HttpServletRequest request,
                                     @RequestParam(value = "token", required = true) String token,
                                     @RequestParam(value = "amt", required = true) BigDecimal amt){
        String[] v = tokenService.getUserByToken(token);
        if (v == null){
            logger.error("token is invalidate.");
            return null;
        }
        logger.info("mobile is " + v[1]);

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setLogin_id(v[1]);  // 账号

        Long longAmt = amt.multiply(new BigDecimal(100)).longValue();
        data.setAmt(longAmt.toString());   // 金额
        data.setPage_notify_url(environment.getProperty("recharge_back_url")); //回掉地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));
        ModelAndView mav = new ModelAndView("/app/app_quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", environment.getProperty("bank_recharge_url"));

        return mav;
    }
}
