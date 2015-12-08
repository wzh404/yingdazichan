package com.xeehoo.p2p.controller;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
//import com.xeehoo.p2p.fuiou.AppSignCardReqData;
import com.xeehoo.p2p.util.MD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WIN10 on 2015/12/5.
 */
@Controller
public class FuiouController {
    private final Logger logger = Logger.getLogger(FuiouController.class);

    @Autowired
    private Environment environment;
    /**
     * 1. 注册个人账户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/userReg", method = RequestMethod.POST)
    public ModelAndView userReg(HttpServletRequest request,
                                @RequestParam(value = "name", required = true) String custName,
                                @RequestParam(value = "cid", required = true) String certifId,
                                @RequestParam(value = "mobile", required = true) String mobile,
                                @RequestParam(value = "email", required = true) String email,
                                @RequestParam(value = "city", required = true) String cityId,
                                @RequestParam(value = "bank", required = true) String bankId,
                                @RequestParam(value = "bankname", required = true) String bankName,
                                @RequestParam(value = "accout", required = true) String accout,
                                @RequestParam(value = "rem", required = true) String rem,
                                @RequestParam(value = "login_pwd", required = true) String loginPassword,
                                @RequestParam(value = "pwd", required = true) String password){
        RegReqData data = new RegReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号

        data.setCust_nm(custName); //用户名称
        data.setCertif_id(certifId); //身份证
        data.setMobile_no(mobile); //手机
        data.setEmail(email);  //邮箱
        data.setCity_id(cityId);  //城市
        data.setParent_bank_id(bankId);  //开户行
        data.setBank_nm(bankName);  //银行名称
        data.setCapAcntNm(""); //提现账户开户名(留空)
        data.setCapAcntNo(accout); //帐号
        data.setRem(rem); //备注

        data.setPassword(MD5.MD5Encode(password)); //提现密码
        data.setLpassword(MD5.MD5Encode(loginPassword)); //登录密码

        logger.info(data.toString());
        CommonRspData rsp = null;
        try {
            rsp = FuiouService.reg(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 24. 法人开户注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/artifReg", method = RequestMethod.POST)
    public ModelAndView artifReg(HttpServletRequest request,
                                 @RequestParam(value = "name", required = true) String custName,
                                 @RequestParam(value = "artifName", required = true) String artifName,
                                 @RequestParam(value = "cid", required = true) String certifId,
                                 @RequestParam(value = "mobile", required = true) String mobile,
                                 @RequestParam(value = "email", required = true) String email,
                                 @RequestParam(value = "city", required = true) String cityId,
                                 @RequestParam(value = "bank", required = true) String bankId,
                                 @RequestParam(value = "bankname", required = true) String bankName,
                                 @RequestParam(value = "accout", required = true) String accout,
                                 @RequestParam(value = "rem", required = true) String rem,
                                 @RequestParam(value = "login_pwd", required = true) String loginPassword,
                                 @RequestParam(value = "pwd", required = true) String password){
        ArtifRegReqData data = new ArtifRegReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号

        data.setCust_nm(custName); //企业名称
        data.setArtif_nm(artifName); //法人姓名
        data.setCertif_id(certifId); //身份证
        data.setMobile_no(mobile); //手机
        data.setEmail(email);  //邮箱
        data.setCity_id(cityId);  //开户行城市
        data.setParent_bank_id(bankId);  //开户行
        data.setBank_nm(bankName);  //开户行支行名称
        data.setCapAcntNo(accout); //帐号

        data.setPassword(MD5.MD5Encode(password)); //提现密码
        data.setLpassword(MD5.MD5Encode(loginPassword)); //登录密码
        data.setRem(rem); //备注

        CommonRspData rsp = null;
        try {
            rsp = FuiouService.artifReg(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 2. 查询明细
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request,
                              @RequestParam(value = "mobile", required = true) String mobile){
        QueryReqData data = new QueryReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setUser_ids(mobile);  // 用户ID
        data.setStart_day("20151101"); //开始日期
        data.setEnd_day("20151130"); //终止日期

        QueryRspData rsp = null;
        try {
            rsp = FuiouService.query(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 3. 查询余额
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ModelAndView balance(HttpServletRequest request,
                                @RequestParam(value = "mobile", required = true) String mobile){
        QueryBalanceReqData data = new QueryBalanceReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no(mobile);  // 用户ID
        data.setMchnt_txn_dt(this.getCurrentDate()); //交易日期

        QueryBalanceRspData rsp = null;
        try {
            rsp = FuiouService.balanceAction(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 21. 冻结到冻结 - 满标放款
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/freeze2freeze", method = RequestMethod.GET)
    public ModelAndView transferBuAndFreeze2Freeze(HttpServletRequest request,
                                                   @RequestParam(value = "mobile", required = true) String outCustNo,
                                                   @RequestParam(value = "mobile2", required = true) String inCustNo,
                                                   @RequestParam(value = "amt", required = true) String amt){
        TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
        data.setAmt(amt); //交易金额
        data.setRem("test"); //备注

        UnFreezeRspData rsp = null;
        try {
            rsp = FuiouService.transferBuAndFreeze2Freeze(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 12. 冻结 - 投标
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/freeze", method = RequestMethod.GET)
    public ModelAndView freeze(HttpServletRequest request,
                               @RequestParam(value = "mobile", required = true) String mobile,
                               @RequestParam(value = "amt", required = true) String v6){
        FreezeReqData data = new FreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no(mobile);  // 冻结账号
        data.setAmt(v6); //冻结金额
        data.setRem("test"); //备注

        CommonRspData rsp = null;
        try {
            rsp = FuiouService.freeze(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 15. 解冻 - 流标
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/unfreeze", method = RequestMethod.GET)
    public ModelAndView unfreeze(HttpServletRequest request,
                                 @RequestParam(value = "mobile", required = true) String mobile,
                                 @RequestParam(value = "amt", required = true) String v6){
        FreezeReqData data = new FreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no(mobile);  // 解冻账号
        data.setAmt(v6); //解冻金额
        data.setRem("test"); //备注


        UnFreezeRspData rsp = null;
        try {
            rsp = FuiouService.unFreeze(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 6.转账(商户与个人之间)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/transferBmu", method = RequestMethod.GET)
    public ModelAndView transferBmu(HttpServletRequest request,
                                    @RequestParam(value = "mobile", required = true) String outCustNo,
                                    @RequestParam(value = "mobile2", required = true) String inCustNo,
                                    @RequestParam(value = "amt", required = true) String amt){
        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
        data.setAmt(amt); //划拨金额
        data.setRem("test"); //备注

        CommonRspData rsp = null;
        try {
            rsp = FuiouService.transferBmu(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 7. 划拨 (个人与个人之间) - 回款
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/transferBu", method = RequestMethod.GET)
    public ModelAndView transferBu(HttpServletRequest request,
                                   @RequestParam(value = "mobile", required = true) String outCustNo,
                                   @RequestParam(value = "mobile2", required = true) String inCustNo,
                                   @RequestParam(value = "amt", required = true) String amt){
        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no(outCustNo);  // 转出账号
        data.setIn_cust_no(inCustNo);   // 转入账号
        data.setAmt(amt); //划拨金额
        data.setRem("test"); //备注

        CommonRspData rsp = null;
        try {
            rsp = FuiouService.transferBu(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }

    /**
     * 20.	交易查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryTxn", method = RequestMethod.POST)
    public ModelAndView queryTxn(HttpServletRequest request,
                                 @RequestParam(value = "cust_no", required = false) String custNo,
                                 @RequestParam(value = "txn_type", required = true) String txnType,
                                 @RequestParam(value = "txn_ssn", required = false) String txnSsn,
                                 @RequestParam(value = "start_date", required = true) String startDate,
                                 @RequestParam(value = "end_date", required = true) String endDate){
        QueryTxnReqData data = new QueryTxnReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号

        if (!StringUtils.isEmpty(custNo))
            data.setCust_no(custNo);  // 交易账号
        data.setBusi_tp(txnType); // txnType交易类型

        if (!StringUtils.isEmpty(txnSsn))
            data.setTxn_ssn(txnSsn);   // 交易流水号

        data.setStart_day("20151201"); //startDate交易起始日期 不能跨月
        data.setEnd_day("20151207"); //交易终止日期

        QueryTxnRspData rsp = null;
        try {
            rsp = FuiouService.queryTxn(data);
            logger.info(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/fuiou/resp", "data", rsp);
    }


    /**
     * 29. 商户P2P网站免登录快速充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/quickRecharge", method = RequestMethod.GET)
    public ModelAndView quickRecharge(HttpServletRequest request,
                                      @RequestParam(value = "mobile", required = true) String mobile,
                                      @RequestParam(value = "amt", required = true) String amt){
        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id(mobile);  // 账号
        data.setAmt(amt);   // 金额
        data.setPage_notify_url(this.getRechargeBackUrl()); //回掉地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", this.getQuickRechargeUrl());
        return mav;
    }

    /**
     * 30.商户P2P网站免登录网银充值接口withdraw
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/bankRecharge", method = RequestMethod.GET)
    public ModelAndView bankRecharge(HttpServletRequest request,
                                     @RequestParam(value = "mobile", required = true) String mobile,
                                     @RequestParam(value = "amt", required = true) String v6){

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id(mobile);  // 账号
        data.setAmt(v6);   // 金额
        data.setPage_notify_url(this.getRechargeBackUrl()); //回掉地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));
        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", this.getBankRechargeUrl());
        return mav;
    }


    /**
     * 31.商户P2P网站免登录提现接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public ModelAndView withdraw(HttpServletRequest request,
                                 @RequestParam(value = "mobile", required = true) String mobile,
                                 @RequestParam(value = "amt", required = false) String v6){

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id(mobile);  // 账号
        data.setAmt(v6);   // 金额
        data.setPage_notify_url(this.getWithdrawBackUrl()); //回掉地址
        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", this.getWithdrawUrl());
        return mav;
    }


    /**
     * 获取商户号
     *
     * @return
     */
    private String getMchntCd(){
        return environment.getProperty("mchnt_cd");
    }

    /**
     * 获取当前日期yyyymmdd
     *
     * @return
     */
    private String getCurrentDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt);
    }

    /**
     * 获取当前交易流水号
     *
     * @return
     */
    private String getMchntTxnSsn(){
        Long ssn = System.nanoTime();
        logger.info("txn_ssn is " + ssn);

        return ssn.toString();
    }

    /**
     * 富友快速充值URL
     *
     * @return
     */
    private String getQuickRechargeUrl(){
        return environment.getProperty("quick_recharge_url");
    }

    /**
     * 富友网银充值URL
     *
     * @return
     */
    private String getBankRechargeUrl(){
        return environment.getProperty("bank_recharge_url");
    }

    /**
     * 富友提现URL
     *
     * @return
     */
    private String getWithdrawUrl(){
        return environment.getProperty("withdraw_url");
    }

    /**
     * 富友充值后回调URL
     *
     * @return
     */
    private String getRechargeBackUrl(){
        return environment.getProperty("recharge_back_url");
    }

    /**
     * 富友取现后回调URL
     *
     * @return
     */
    private String getWithdrawBackUrl(){
        return environment.getProperty("withdraw_back_url");
    }
}
