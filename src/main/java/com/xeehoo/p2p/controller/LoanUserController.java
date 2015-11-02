package com.xeehoo.p2p.controller;

import com.xeehoo.p2p.mybatis.mapper.UserMapper;
import com.xeehoo.p2p.po.LoanUserFund;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.service.LoanCacheService;
import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.IDCard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
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
    private LoanStaffService staffService;

    @Autowired
    private LoanCacheService cacheService;

    @Autowired
    private Environment environment;

    /**
     * 新用户注册第一步
     *
     * @param loginName 登录名
     * @param loginPwd  登陆密码
     * @param inviteCode  邀请码
     * @return  登录页
     */
    @RequestMapping(value = "/reguser", method = RequestMethod.POST)
    public ModelAndView reguser(HttpServletRequest request,
                                @RequestParam(value = "txtUserCode", required = true) String loginName,
                                @RequestParam(value = "txtPassword", required = true) String loginPwd,
                                @RequestParam(value = "txtInviteCode", required = true) String inviteCode) {
        LoanUser userInfo = new LoanUser();
        userInfo.setLoginName(loginName);
        userInfo.setLoginPwd(loginPwd);
        userInfo.setInviteCode(inviteCode);

        // 检查邀请手机是否注册手机号
        if (!userService.checkUser(inviteCode, "MOBILE")) {
            return CommonUtil.createErrorModelAndView("/user/register",
                    messageSource.getMessage("register.invalid.invitecode", null, "", null));
        }

        // 保存第一步注册信息到session中
        HttpSession session = request.getSession(true);
        session.setAttribute(Constant.SESSION_REGISTER_STEP, userInfo);
        return new ModelAndView("/user/reg2");
    }

    /**
     * 新用户注册第二步
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
        LoanUser userInfo = (LoanUser) session.getAttribute(Constant.SESSION_REGISTER_STEP);
        if (userInfo == null) {
            return CommonUtil.createErrorModelAndView("/user/register",
                    messageSource.getMessage("register.invalid.info", null, "", null));
        }

        // 检查手机是否已注册
        if (userService.checkUser(mobile, "MOBILE")) {
            return CommonUtil.createErrorModelAndView("/user/reg2",
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
        session.removeAttribute(Constant.SESSION_REGISTER_STEP);

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
        int ret = loginv("name", request, response, loginName, loginPwd);

        staffService.staffLogin("盈达官方", "123456");
        if (ret == Constant.LOGIN_INVALID_PWD) {
            return CommonUtil.createErrorModelAndView("index",
                    messageSource.getMessage("login.invalid.pwd", null, "", null));
        } else if (ret == Constant.LOGIN_INVALID_NAME) {
            return CommonUtil.createErrorModelAndView("index",
                    messageSource.getMessage("login.invalid.name", null, "", null));
        }

        return new ModelAndView("index");
    }

    /**
     * 获取用户资产情况
     *
     * @return
     */
    @RequestMapping(value = "/fund", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView fund(HttpServletRequest request) {
        SessionObject so = CommonUtil.getSessionObject(request, null);
        if (so == null) {
            return new ModelAndView("/index");
        }
        Integer userID = so.getUserID();
        LoanUserFund userFund = userService.getFundByUserID(userID);

        return new ModelAndView("/user/home", "userFund", userFund);
    }

    /**
     * 用户登录业务逻辑
     *
     * @param request
     * @param response
     * @param loginName
     * @param loginPwd
     * @return
     */
    private int loginv(String loginType, HttpServletRequest request, HttpServletResponse response, String loginName, String loginPwd) {
        String host = CommonUtil.getRemoteHost(request);
        Optional<LoanUser> userInfo = userService.login(loginType, loginName, loginPwd);
        if (!userInfo.isPresent()) {
            return Constant.LOGIN_INVALID_NAME;
        }

        if (userInfo.get().getUserStatus() != Constant.LOGIN_OK) {
            HttpSession session = request.getSession();
            if (session != null)
                session.invalidate();

            return userInfo.get().getUserStatus();
        }

        logger.info(loginName + " - " + host);
        String token = CommonUtil.generateToken(loginName, host);

        // 设置cookie
        logger.info("client-side-token: " + token);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2 * 60 * 60);
        response.addCookie(cookie);

        // 保存session对象
        SessionObject so = new SessionObject();
        so.setLoginName(loginName);
        so.setToken(token);
        so.setUserID(userInfo.get().getUserId());
        HttpSession session = request.getSession(true);
        CommonUtil.setSessionObject(session, token, so);

        return Constant.LOGIN_OK;
    }

    /*---------------------------Ajax controller--------------------------------*/

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
        int ret = loginv("name", request, response, loginName, loginPwd);
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
     * 发送注册授权码
     *
     * @param mobile 手机号
     * @return
     */
    @RequestMapping(value = "/ajax/authcode", method = RequestMethod.GET)
    @ResponseBody
    public Map sendAuthCode(@RequestParam(value = "mobile", required = true) String mobile) {
        String authCode = CommonUtil.generateAuthCode();
        cacheService.setMobileAuthCode(mobile, authCode);

        return CommonUtil.generateJsonMap("OK", authCode);
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
    @RequestMapping(value = "/ajax/mobile", method = RequestMethod.GET)
    @ResponseBody
    public Map checkUserMobile(@RequestParam(value = "mobile", required = true) String mobile,
                               @RequestParam(value = "send", required = false) Boolean sendAuthCode) {
        boolean exist = userService.checkUser("mobile", mobile);

        Map map ;
        if (exist) {
            map = CommonUtil.generateJsonMap("OK",
                    messageSource.getMessage("register.mobile.exists", null, "", null));
        } else {
            map = CommonUtil.generateJsonMap("NO", null);
        }

        if (sendAuthCode != null && sendAuthCode) {
            String authCode = CommonUtil.generateAuthCode();
            logger.info("mobile authcode is " + authCode);
            cacheService.setMobileAuthCode(mobile, authCode);
        }
        return map;

    }

    /**
     * 检查用户手机号是否被注册
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
