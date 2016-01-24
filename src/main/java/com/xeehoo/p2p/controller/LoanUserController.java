package com.xeehoo.p2p.controller;

import com.fuiou.data.*;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.cache.Cache;
import com.xeehoo.p2p.cache.impl.HttpSessionCache;
import com.xeehoo.p2p.po.LoanUserFund;
import com.xeehoo.p2p.po.LoanUserInvestment;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.service.LoanCacheService;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wangzunhui on 2015/9/25.
 */
@Controller
public class LoanUserController {
    private final Logger logger = Logger.getLogger(LoanUserController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LoanUserService userService;

    @Autowired
    private LoanInvestService investService;

    @Autowired
    private LoanCacheService cacheService;

    @Autowired
    private Environment environment;


    /**
     * 新用户注册第一步
     *
     * @param mobile 登录名
     * @param loginPwd  登陆密码
     * @param valideCode 验证码
     * @return  登录页
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request,
                                @RequestParam(value = "mobile", required = true) String mobile,
                                @RequestParam(value = "pwd", required = true) String loginPwd,
                                @RequestParam(value = "vcode", required = true) String valideCode) {
        LoanUser userInfo = new LoanUser();
        userInfo.setLoginName(mobile);
        userInfo.setLoginPwd(loginPwd);
        userInfo.setMobile(mobile);

        Cache cache = new HttpSessionCache(request.getSession());
        if (!checkValideCode(cache, valideCode)){
            return new ModelAndView("/user/register_step_1");
        }

        sendAuthCode(cache, mobile);
        cache.set(Constant.SESSION_REGISTER_USER, userInfo);

        return new ModelAndView("/user/register_step_2");
    }

    /**
     * 新用户注册第二步
     *
     * @param request
     * @param authCode
     * @return
     */
    @RequestMapping(value = "/register2", method = RequestMethod.POST)
    public ModelAndView register2(HttpServletRequest request,
                                @RequestParam(value = "authcode", required = true) String authCode) {
        Cache cache = new HttpSessionCache(request.getSession());
        LoanUser user = (LoanUser)cache.get(Constant.SESSION_REGISTER_USER);
        if (user == null){
            return new ModelAndView("/user/register_step_1");
        }

        /* 短信认证
        String cacheAuthCode = (String)cache.get(user.getMobile());
        if (cacheAuthCode == null || !cacheAuthCode.equalsIgnoreCase(authCode)){
            sendAuthCode(cache, user.getMobile());
            return new ModelAndView("/user/register_step_2");
        }*/

        if (!authCode.equalsIgnoreCase("0000")){
            return new ModelAndView("/user/register_step_2");
        }

        // 用户状态为正常
        user.setUserStatus(Constant.USER_STATUS_NORMAL);

        // 清除第一步的session数据
        cache.remove(Constant.SESSION_REGISTER_USER);

        // 注册IP地址
        String host = CommonUtil.getRemoteHost(request);
        user.setRegisterIP(host);

        // 保存注册用户
        Integer userID = userService.save(user);
        if (userID > 0) {
            return new ModelAndView("/user/register_step_3");
        }

        return new ModelAndView("error");
    }

    /**
     * 新用户注册第三步
     *
     * @param mobile
     * @param authCode
     * @return
     */
    @RequestMapping(value = "/reg2", method = RequestMethod.POST)
    public ModelAndView reg2(HttpServletRequest request,
                             @RequestParam(value = "txtMobile", required = true) String mobile,
                             @RequestParam(value = "txtShouJiMa", required = true) String authCode) {
        HttpSession session = request.getSession();
        // 取出上一步注册的信息。
        LoanUser userInfo = (LoanUser) session.getAttribute(Constant.SESSION_REGISTER_USER);
        if (userInfo == null) {
            return CommonUtil.createErrorModelAndView("/user/register_step_1",
                    messageSource.getMessage("register.invalid.info", null, "", null));
        }

        // 检查手机是否已注册
        if (userService.checkUser(mobile, "MOBILE")) {
            return CommonUtil.createErrorModelAndView("/user/register_step_2",
                    messageSource.getMessage("register.mobile.exists", null, "", null));
        }
        userInfo.setMobile(mobile);

        // 检查手机动态码是否正确
        String authCodeInCache = cacheService.getMobileAuthCode(mobile);
        if (authCodeInCache == null || authCode == null || !authCode.equals(authCodeInCache)) {
            return CommonUtil.createErrorModelAndView("/user/reg2",
                    messageSource.getMessage("register.invalid.authcode", null, "", null));
        }

        // 用户状态为正常
        userInfo.setUserStatus(Constant.USER_STATUS_NORMAL);

        // 清除第一步的session数据
        session.removeAttribute(Constant.SESSION_REGISTER_USER);

        // 注册IP地址
        String host = CommonUtil.getRemoteHost(request);
        userInfo.setRegisterIP(host);

        // 保存注册用户
        Integer userID = userService.save(userInfo);
        if (userID > 0) {
            return new ModelAndView("index");
        }

        return new ModelAndView("error");
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate();

        return new ModelAndView("index");
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "login_name", required = true) String loginName,
                              @RequestParam(value = "login_pwd", required = true) String loginPwd) {
        int ret = login(request, response, "name", loginName, loginPwd);
        if (ret == Constant.LOGIN_INVALID_PWD) {
            return CommonUtil.createErrorModelAndView("/user/login",
                    messageSource.getMessage("login.invalid.pwd", null, "", null));
        } else if (ret == Constant.LOGIN_INVALID_NAME) {
            return CommonUtil.createErrorModelAndView("/user/login",
                    messageSource.getMessage("login.invalid.name", null, "", null));
        }

        return new ModelAndView("index");
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/user/changePwd", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView changePwd(HttpServletRequest request,
                                  @RequestParam(value = "old_pwd", required = true) String oldLoginPwd,
                                  @RequestParam(value = "new_pwd", required = true) String newLoginPwd) {
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        if (userService.changeLoginPwd(so.getUserID(), oldLoginPwd, newLoginPwd)){
            return new ModelAndView("/user/user_change_pwd");
        }

        return new ModelAndView("/user/user_change_pwd");
    }

    /**
     * 获取用户资产情况
     *
     * @return
     */
    @RequestMapping(value = "/user/fund", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView fund(HttpServletRequest request) {
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }
        Integer userID = so.getUserID();
        LoanUserFund userFund = userService.getFundByUserID(userID);
        Map<String, BigDecimal> map = queryBalance(so.getLoginName());
        ModelAndView mav = new ModelAndView("/user/user_fund");
        mav.addObject("balance", map);
        mav.addObject("userFund", userFund);

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

            map.put("ct", new BigDecimal(ct / 100.0));
            map.put("ca", new BigDecimal(ca / 100.0));
            map.put("cf", new BigDecimal(cf / 100.0));
            map.put("cu", new BigDecimal(cu / 100.0));
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

    /**
     * 进入用户取现
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/enterUserWithdraw", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView enterUserWithdraw(HttpServletRequest request){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        ModelAndView mav = new ModelAndView("/user/user_withdraw");
        Map<String, BigDecimal> map = queryBalance(so.getLoginName());

        mav.addObject("balance", map);
        return mav;
    }

    /**
     * 进入用户认证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/enterUserSecurity", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView enterUserSecurity(HttpServletRequest request){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        ModelAndView mav = new ModelAndView("/user/user_security");
        Map<String, BigDecimal> map = queryBalance(so.getLoginName());

        mav.addObject("balance", map);
        mav.addObject("account", StringUtils.isEmpty(so.getEscrowAccount()));

        return mav;
    }

    /**
     * 进入用户充值
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/enterUserRecharge", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView enterUserRecharge(HttpServletRequest request){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        ModelAndView mav = new ModelAndView("/user/user_recharge");
        Map<String, BigDecimal> map = queryBalance(so.getLoginName());

        mav.addObject("balance", map);
        return mav;
    }

    /**
     * 用户投资项目
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/invest", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userInvest(HttpServletRequest request,
                                   @RequestParam(value = "due", required = false) String due,
                                   @RequestParam(value = "page", required = false) Integer page){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        if (due == null) due = "A";
        if (page == null) page = 0;

        QueryCondition queryCondition = new QueryCondition();
        queryCondition.put("_userId", so.getUserID());
        queryCondition.put("due", due);
        LoanPagedListHolder pagedListHolder = investService.getUserInvestments(page, queryCondition);

        ModelAndView mav = new ModelAndView("/user/user_investment");
        queryCondition.setModelAndView(request.getRequestURI(), mav);
        mav.addObject("pagedListHolder", pagedListHolder);

        return mav;
    }

    /**
     * 商户P2P网站免登录网银充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/bankRecharge", method = RequestMethod.GET)
    public ModelAndView bankRecharge(HttpServletRequest request,
                                     @RequestParam(value = "amt", required = true) BigDecimal amt){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setLogin_id(so.getLoginName());  // 账号

        Long longAmt = amt.multiply(new BigDecimal(100)).longValue();
        data.setAmt(longAmt.toString());   // 金额
        data.setPage_notify_url(environment.getProperty("recharge_back_url")); //回掉地址

        data.setSignature(SecurityUtils.sign(data.getSignature()));
        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", environment.getProperty("bank_recharge_url"));

        return mav;
    }

    /**
     * 31.商户P2P网站免登录提现接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/withdraw", method = RequestMethod.GET)
    public ModelAndView withdraw(HttpServletRequest request,
                                 @RequestParam(value = "amt", required = true) BigDecimal amt){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        AppTransReqData data = new AppTransReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setLogin_id(so.getLoginName());  // 账号

        Long longAmt = amt.multiply(new BigDecimal(100)).longValue();
        data.setAmt(longAmt.toString());   // 金额

        data.setPage_notify_url(environment.getProperty("withdraw_back_url")); //回掉地址
        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/quick_recharge");
        mav.addObject("data", data);
        mav.addObject("action", environment.getProperty("withdraw_url"));

        return mav;
    }

    /**
     * 富友页面注册（认证）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/webReg", method = RequestMethod.GET)
    public ModelAndView webReg(HttpServletRequest request){
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/user/login");
        }

        WebRegReqData data = new WebRegReqData();
        data.setMchnt_cd(environment.getProperty("mchnt_cd")); // 商户号
        data.setMchnt_txn_ssn(CommonUtil.getMchntTxnSsn()); //流水号
        data.setMobile_no(so.getLoginName());  // 账号
        data.setPage_notify_url(environment.getProperty("webreg_back_url")); //回调地址
        data.setSignature(SecurityUtils.sign(data.getSignature()));

        ModelAndView mav = new ModelAndView("/fuiou/web_reg");
        mav.addObject("data", data);
        mav.addObject("action", "webReg");

        return mav;
    }

    /*---------------------------- admin ----------------------*/
    @RequestMapping(value="/admin/listUser")
    @Permission("0201")
    public ModelAndView execute(HttpServletRequest request,
                                @RequestParam(value = "login_name", required = false) String loginName,
                                @RequestParam(value = "user_status", required = false) Integer userStatus,
                                @RequestParam(value = "real_name", required = false) String realName,
                                @RequestParam(value = "id_card", required = false) String idCard,
                                @RequestParam(value = "page", required = false) Integer page) {
        if (page == null) page = 0;
        Map<String, Object> cond = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("/admin/list_user");

        if (!StringUtils.isEmpty(loginName)){
            cond.put("login_name", loginName);
            mav.addObject("login_name", loginName);
        }
        if (!StringUtils.isEmpty(realName)){
            cond.put("real_name", realName);
            mav.addObject("real_name", realName);
        }
        if (!StringUtils.isEmpty(idCard)){
            cond.put("id_card", idCard);
            mav.addObject("id_card", idCard);
        }
        cond.put("user_status", userStatus);

        LoanPagedListHolder pagedListHolder = userService.getUserPager(page, cond);
        mav.addObject("pagedListHolder", pagedListHolder);
        mav.addObject("user_status", userStatus);

        return mav;
    }

    /*---------------------------- private --------------------*/

    /**
     * 用户登录业务逻辑
     *
     * @param request
     * @param response
     * @param loginName
     * @param loginPwd
     * @return
     */
    private int login(HttpServletRequest request,
                      HttpServletResponse response,
                      String loginType,
                      String loginName,
                      String loginPwd) {
        HttpSession session = request.getSession();
        Cache cache = new HttpSessionCache(session);
//        checkValideCode(cache, )

        // get client ip address
        String host = CommonUtil.getRemoteHost(request);
        Optional<LoanUser> userInfo = userService.login(loginType, loginName, loginPwd);
        if (!userInfo.isPresent()) {
            return Constant.LOGIN_INVALID_NAME;
        }

        if (userInfo.get().getUserStatus() != Constant.LOGIN_OK) {
            session.invalidate();
            return userInfo.get().getUserStatus();
        }

        //生成token
        logger.info(loginName + " - " + host);
        String token = CommonUtil.generateToken(loginName, host);

        // 设置cookie
        logger.info("client-side-token: " + token);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2 * 60 * 60);
        response.addCookie(cookie);

        // 保存登录信息到cache
        SessionObject so = new SessionObject();
        so.setLoginName(loginName);
        so.setEscrowAccount(userInfo.get().getEscrowAccount());
        so.setToken(token);
        so.setUserID(userInfo.get().getUserId());
        cache.set(Constant.SESSION_USER_LOGIN, so);

        return Constant.LOGIN_OK;
    }


    /**
     * 发送短信验证码
     *
     * @param cache
     * @param mobile
     */
    private void sendAuthCode(Cache cache, String mobile){
        String authCode = CommonUtil.generateAuthCode();
        logger.info("**** " + mobile + " - " + authCode);
        cache.set(mobile, authCode);
    }

    /**
     * 检查网站校验码
     *
     * @param cache
     * @param vcode
     * @return
     */
    private boolean checkValideCode(Cache cache, String vcode){
        String cacheValidecode = (String)cache.get(Constant.SESSION_VALIDATE_CODE);

        return (cacheValidecode != null && cacheValidecode.equalsIgnoreCase(vcode));
    }

    /*---------------------------Ajax controller----------------------*/

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/ajax/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map ajaxLogin(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value = "login_name", required = true) String loginName,
                         @RequestParam(value = "login_pwd", required = true) String loginPwd) {
        Map map = CommonUtil.generateJsonMap("OK", null);
        int ret = login(request, response, "name", loginName, loginPwd);
        if (ret == 2) {
            map = CommonUtil.generateJsonMap("ERROR",
                    messageSource.getMessage("login.invalid.pwd", null, "", null));
        } else if (ret == 1) {
            map = CommonUtil.generateJsonMap("ERROR",
                    messageSource.getMessage("login.invalid.name", null, "", null));
        }

        return map;
    }

    /**
     * 发送手机注册验证码
     *
     * @param mobile 手机号
     * @return
     */
    @RequestMapping(value = "/ajax/sendauthcode", method = RequestMethod.GET)
    @ResponseBody
    public Map sendAuthCode(HttpServletRequest request,
                            @RequestParam(value = "mobile", required = true) String mobile) {
        HttpSession session = request.getSession();
        Cache cache = new HttpSessionCache(session);
        LoanUser user = (LoanUser)cache.get(Constant.SESSION_REGISTER_USER);
        if (user != null && user.getMobile().equalsIgnoreCase(mobile)){
            sendAuthCode(cache, mobile);
            return CommonUtil.generateJsonMap("OK", "send ok");
        }

        return CommonUtil.generateJsonMap("ERROR", "invalid request!");
    }

    /**
     * 检查用户名是否被注册
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/ajax/username", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkUserName(@RequestParam(value = "name", required = true) String name) {
        boolean exist = userService.checkUser("name", name);

        if (exist) {
            return CommonUtil.generateJsonMap("OK", messageSource.getMessage("register.name.exists", null, "", null));
        } else {
            return CommonUtil.generateJsonMap("NO", null);
        }
    }

    /**
     * 检查用户手机号是否被注册
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/ajax/checkMobile", method = RequestMethod.GET)
    @ResponseBody
    public Map checkUserMobile(@RequestParam(value = "mobile", required = true) String mobile) {
        boolean exist = userService.checkUser("mobile", mobile);

        Map map ;
        if (exist) {
            map = CommonUtil.generateJsonMap("OK",
                    messageSource.getMessage("register.mobile.exists", null, "", null));
        } else {
            map = CommonUtil.generateJsonMap("NO", null);
        }

        return map;

    }

    /**
     *
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/ajax/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadIdCardImage(@RequestParam("file") MultipartFile[] files) {
        if (files == null || files.length <= 0) {
            logger.info("no file upload!");
            return CommonUtil.generateJsonMap("ERROR", null);
        }

        String uploadPath = environment.getProperty("upload.user");
        try {
            for (MultipartFile file : files) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                file.transferTo(new File(uploadPath + "/" + uuid));
                logger.info("transfer to " + uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.generateJsonMap("ERROR", null);
        }

        return CommonUtil.generateJsonMap("OK", null);
    }
}
