package com.xeehoo.p2p.controller.app;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.service.TokenService;
import com.xeehoo.p2p.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * 17. APP页面注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/fuiou/register", method = RequestMethod.GET)
    public ModelAndView webReg(HttpServletRequest request,
                               @RequestParam(value = "token", required = true) String token) {
        logger.info("token is " + token);
        String[] v = tokenService.getUserByToken(token);
        if (v == null) {
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
     * 27. 商户APP网站免登录网银充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/fuiou/recharge", method = RequestMethod.GET)
    public ModelAndView bankRecharge(HttpServletRequest request,
                                     @RequestParam(value = "token", required = true) String token,
                                     @RequestParam(value = "amt", required = true) String amt) {
        String[] v = tokenService.getUserByToken(token);
        if (v == null) {
            logger.error("token is invalidate.");
            return null;
        }
        logger.info("mobile is " + v[1]);

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); // 流水号
        data.setLogin_id(v[1]);  // 账号

//        Long longAmt = amt.multiply(new BigDecimal(100)).longValue();
        data.setAmt(amt);   // 金额
        data.setPage_notify_url(environment.getProperty("recharge_back_url")); //回调地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));
        ModelAndView mav = new ModelAndView("/app/app_quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", environment.getProperty("bank_recharge_url"));

        return mav;
    }

    /**
     * 28.商户APP网站免登录提现接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/fuiou/withdraw", method = RequestMethod.GET)
    public ModelAndView withdraw(HttpServletRequest request,
                                 @RequestParam(value = "token", required = true) String token,
                                 @RequestParam(value = "amt", required = false) BigDecimal amt) {
        String[] v = tokenService.getUserByToken(token);
        if (v == null) {
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
        data.setPage_notify_url(environment.getProperty("withdraw_back_url")); //回调地址
        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/app/app_quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", environment.getProperty("withdraw_url"));
        return mav;
    }

    /**
     * 3. 查询余额
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/app/fuiou/balance", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> balance(@RequestHeader(value = "authorization", required = true) String token) {
        String[] v = tokenService.getUserByToken(token);
        if (v == null) {
            logger.error("token is invalidate.");
            return null;
        }
        logger.info("mobile is " + v[1]);
        return bal(v[1]);
//        QueryBalanceReqData data = new QueryBalanceReqData();
//        data.setMchnt_cd(environment.getProperty("mchnt_cd")); //商户号
//        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
//        data.setCust_no(v[1]);  // 用户ID
//        data.setMchnt_txn_dt(getCurrentDate()); //交易日期
//
//        QueryBalanceRspData rsp = null;
//        try {
//            rsp = FuiouService.balanceAction(data);
//            logger.info(rsp.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            return CommonUtil.generateJsonMap("EX99", "查询失败");
//        }
//
//        if ("0000".equalsIgnoreCase(rsp.getResp_code())) {
//            Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
//            QueryBalanceResultData resultData = rsp.getResults().get(0);
//
//            map.put("ca", resultData.getCa_balance());
//            map.put("cf", resultData.getCf_balance());
//            map.put("ct", resultData.getCt_balance());
//            map.put("cu", resultData.getCu_balance());
//
//            return map;
//
//        } else {
//            return CommonUtil.generateJsonMap("EX20", rsp.getResp_code());
//        }
    }

    /**
     * 3. 查询用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/app/fuiou/user", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryUser(@RequestHeader(value = "authorization", required = true) String token) {
        String[] v = tokenService.getUserByToken(token);
        if (v == null) {
            logger.error("token is invalidate.");
            return null;
        }

        logger.info("mobile is " + v[1]);

        QueryUserInfsReqData data = new QueryUserInfsReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); //商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setMchnt_txn_dt(getCurrentDate()); //交易日期
        data.setUser_ids(v[1]);  // 用户ID

        try {
            QueryUserInfsRspData rspData = FuiouService.queryUserInfs(data);

            if ("0000".equalsIgnoreCase(rspData.getResp_code())){
                String custName = rspData.getResults().get(0).getCust_nm();
                if (StringUtils.isEmpty(custName)){
                    return CommonUtil.generateJsonMap("ER31", rspData.getResp_code());
                }
                Map<String, Object> map = bal(v[1]);
                map.put("name", custName);

                return map;
            }
            else{
                return CommonUtil.generateJsonMap("ER30", rspData.getResp_code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.generateJsonMap("EX99", "查询失败");
        }
    }

    /**
     * 获取当前日期yyyymmdd
     *
     * @return
     */
    private String getCurrentDate() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt);
    }

    /**
     * 查询用户账户余额
     *
     * @param mobile
     * @return
     */
    private Map<String, Object> bal(String mobile){
        QueryBalanceReqData data = new QueryBalanceReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); //商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setCust_no(mobile);  // 用户ID
        data.setMchnt_txn_dt(getCurrentDate()); //交易日期

        QueryBalanceRspData rsp = null;
        try {
            rsp = FuiouService.balanceAction(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();

            return CommonUtil.generateJsonMap("EX99", "查询失败");
        }

        if ("0000".equalsIgnoreCase(rsp.getResp_code())) {
            Map<String, Object> map = CommonUtil.generateJsonMap("OK", null);
            QueryBalanceResultData resultData = rsp.getResults().get(0);

            map.put("ca", resultData.getCa_balance());
            map.put("cf", resultData.getCf_balance());
            map.put("ct", resultData.getCt_balance());
            map.put("cu", resultData.getCu_balance());

            return map;

        } else {
            return CommonUtil.generateJsonMap("ER20", rsp.getResp_code());
        }
    }
}
