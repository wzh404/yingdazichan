package com.xeehoo.p2p.controller;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
//import com.xeehoo.p2p.fuiou.AppSignCardReqData;
import com.xeehoo.p2p.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/userReg", method = RequestMethod.GET)
    public ModelAndView userReg(HttpServletRequest request){
        RegReqData data = new RegReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号

        data.setCust_nm("王尊会"); //用户名称
        data.setCertif_id("610425197204042611"); //身份证
        data.setMobile_no("18611330404"); //手机
        data.setEmail("wzh404@sina.com");  //邮箱
        data.setCity_id("1000");  //城市
        data.setParent_bank_id("0308");  //开户行
        data.setBank_nm("招商银行股份有限公司上海联洋支行");  //银行名称
        data.setCapAcntNm(""); //提现账户开户名(留空)
        data.setCapAcntNo("6225880159846000"); //帐号
        data.setRem("test"); //备注

        data.setPassword(MD5.MD5Encode("123456")); //登录密码
        data.setLpassword(MD5.MD5Encode("654321")); //支付密码

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
    @RequestMapping(value = "/artifReg", method = RequestMethod.GET)
    public ModelAndView artifReg(HttpServletRequest request){
        ArtifRegReqData data = new ArtifRegReqData();
        data.setMchnt_cd("0002900F0339996"); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号

        data.setCust_nm("北京盈达资产"); //企业名称
        data.setArtif_nm("宋微"); //法人姓名
        data.setCertif_id("210702197702071244"); //身份证
        data.setMobile_no("18611330404"); //手机
        data.setEmail("wzh404@sina.com");  //邮箱
        data.setCity_id("1000");  //开户行城市
        data.setParent_bank_id("0308");  //开户行
        data.setBank_nm("招商银行股份有限公司上海联洋支行");  //开户行支行名称
        data.setCapAcntNo("6225880159846123"); //帐号

        data.setPassword(MD5.MD5Encode("123456")); //登录密码
        data.setLpassword(MD5.MD5Encode("654321")); //支付密码
        data.setRem("test"); //备注

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
    public ModelAndView query(HttpServletRequest request){
        QueryReqData data = new QueryReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setUser_ids("18611330404");  // 用户ID
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
    public ModelAndView balance(HttpServletRequest request){
        QueryBalanceReqData data = new QueryBalanceReqData();
        data.setMchnt_cd(this.getMchntCd()); //商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no("18611330404");  // 用户ID
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
    public ModelAndView transferBuAndFreeze2Freeze(HttpServletRequest request){
        TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no("18611330404");  // 转出账号
        data.setIn_cust_no("13911709225");   // 转入账号
        data.setAmt("1000"); //交易金额
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
                               @RequestParam(value = "amt", required = false) String v6){
        FreezeReqData data = new FreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no("18611330404");  // 冻结账号
        if (StringUtils.isEmpty(v6))
            v6 = "100000";
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
                                 @RequestParam(value = "amt", required = false) String v6){
        FreezeReqData data = new FreezeReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setCust_no("18611330404");  // 解冻账号
        if (StringUtils.isEmpty(v6))
            v6 = "100000";
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
    public ModelAndView transferBmu(HttpServletRequest request){
        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no("18611330404");  // 转出账号
        data.setIn_cust_no("13911709225");   // 转入账号
        data.setAmt("1000"); //划拨金额
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
    public ModelAndView transferBu(HttpServletRequest request){
        TransferBmuReqData data = new TransferBmuReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setOut_cust_no("18611330404");  // 转出账号
        data.setIn_cust_no("13911709225");   // 转入账号
        data.setAmt("1000"); //划拨金额
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
     * 29. 商户P2P网站免登录快速充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/quickRecharge", method = RequestMethod.GET)
    public ModelAndView quickRecharge(HttpServletRequest request){
        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id("18611330404");  // 账号
        data.setAmt("10000");   // 金额

        data.setPage_notify_url("http://119.254.84.20:8080/rechargeResp"); //回掉地址



        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", "500001");
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
                                     @RequestParam(value = "amt", required = false) String v6){

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id("18611330404");  // 账号

        if (StringUtils.isEmpty(v6))
            v6 = "100000";
        data.setAmt(v6);   // 金额

        data.setPage_notify_url("http://119.254.84.20:8080/rechargeResp"); //回掉地址



        data.setSignature(SecurityUtils.sign(data.getSignature()));
        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", "500002");
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
                                 @RequestParam(value = "amt", required = false) String v6){

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(this.getMchntCd()); // 商户号
        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
        data.setLogin_id("18611330404");  // 账号

        if (StringUtils.isEmpty(v6))
            v6 = "100000";
        data.setAmt(v6);   // 金额
        data.setPage_notify_url("http://119.254.84.20:8080/rechargeResp"); //回掉地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", "500003");
        return mav;
    }


//    /**
//     * 36.免登签约
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/sign", method = RequestMethod.GET)
//    public ModelAndView sign(HttpServletRequest request){
//        AppSignCardReqData data = new AppSignCardReqData();
//        data.setMchnt_cd(this.getMchntCd()); // 商户号
//        data.setMchnt_txn_ssn(this.getMchntTxnSsn()); //流水号
//        data.setLogin_id("18611330404");  // 转出账号
//        data.setMobile("18611330404");   // 转入账号
//        data.setPage_notify_url("http://119.254.84.20:8080/rechargeResp"); //备注
//
//
//
//        data.setSignature(SecurityUtils.sign(data.createSignValue()));
//
//        return new ModelAndView("/fuiou/sign_card", "data", data);
//    }



    /**
     * 获取商户号
     *
     * @return
     */
    private String getMchntCd(){
        return environment.getProperty("mchnt_cd");
    }

    private String getCurrentDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt);
    }

    private String getMchntTxnSsn(){
        Long ssn = System.nanoTime();
        logger.info("txn_ssn is " + ssn);

        return ssn.toString();
    }
}
