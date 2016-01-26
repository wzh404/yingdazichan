package com.xeehoo.p2p.controller.app;

import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.service.TokenService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by WIN10 on 2016/1/24.
 */
@Controller
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private LoanUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * APP登录
     *
     * @param request
     * @param name    登录
     * @param pwd     密码
     * @return
     */
    @RequestMapping(value = "/app/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "pwd", required = true) String pwd) {
        LoanUser user = userService.getUser(name);
        if (user == null) {
            return CommonUtil.generateJsonMap("ER10", "用户不存在");
        }

        if (!user.isNormal()){
            return CommonUtil.generateJsonMap("ER15", "用户已冻结");
        }

        if (user.isEqualPwd(pwd)) {
            String token = TokenUtil.generateAuthorizationToken(name);
            tokenService.put(token, user.getUserId().toString() + "," + name, 10);

            return CommonUtil.generateJsonMap("OK", token);
        } else {
            return CommonUtil.generateJsonMap("ER11", "密码不正确");
        }
    }

    /**
     * 修改登录密码
     *
     * @return
     */
    @RequestMapping(value = "/app/changePwd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> changePwd(HttpServletRequest request,
                                         @RequestParam(value = "old_pwd", required = true) String oldLoginPwd,
                                         @RequestParam(value = "new_pwd", required = true) String newLoginPwd,
                                         @RequestParam(value = "token", required = true) String token) {
        Integer userId = tokenService.getUserId(token);
        if (userId == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        if (userService.changeLoginPwd(userId, oldLoginPwd, newLoginPwd)) {
            return CommonUtil.generateJsonMap("ER14", "修改密码失败");
        }

        return CommonUtil.generateJsonMap("OK", null);
    }

    /**
     * 修改支付密码
     *
     * @return
     */
    @RequestMapping(value = "/app/changePayPwd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> changePayPwd(HttpServletRequest request,
                                            @RequestParam(value = "old_pwd", required = true) String oldLoginPwd,
                                            @RequestParam(value = "new_pwd", required = true) String newLoginPwd,
                                            @RequestParam(value = "token", required = true) String token) {
        Integer userId = tokenService.getUserId(token);
        if (userId == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        if (userService.changePayPwd(userId, oldLoginPwd, newLoginPwd)) {
            return CommonUtil.generateJsonMap("ER14", "修改支付密码失败");
        }

        return CommonUtil.generateJsonMap("OK", null);
    }

    /**
     * 设置支付密码
     *
     * @return
     */
    @RequestMapping(value = "/app/setPayPwd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> setPayPwd(@RequestParam(value = "new_pwd", required = true) String newLoginPwd,
                                         @RequestParam(value = "smscode", required = true) String smscode,
                                         @RequestParam(value = "token", required = true) String token) {
        String[] user = tokenService.getUserByToken(token);
        if (user == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        Integer userId = Integer.parseInt(user[0]);
        String tokenSmsCode = tokenService.get("SMS_" + user[1]);
        if (!smscode.equalsIgnoreCase(tokenSmsCode)) {
            return CommonUtil.generateJsonMap("ER91", "输入验证码错误");
        }

        if (userService.updatePayPwd(userId, newLoginPwd)) {
            return CommonUtil.generateJsonMap("ER14", "修改支付密码失败");
        }

        return CommonUtil.generateJsonMap("OK", null);
    }

    /**
     * 发送手机验证码
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/app/sendSms", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> sendSms(@RequestParam(value = "token", required = true) String token) {
        String[] user = tokenService.getUserByToken(token);
        if (user == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        String smscode = CommonUtil.generateAuthCode();
        logger.info(user[1] + " smscode is " + smscode);
        tokenService.put("SMS_" + user[1], smscode, 5);

        return CommonUtil.generateJsonMap("OK", null);
    }

    /**
     * 未登录用户，检查验证手机号
     * @param mobile
     * @param smscode
     *
     * @return
     */
    @RequestMapping(value = "/app/checkSmscode", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> checkSmscode(@RequestParam(value = "mobile", required = true) String mobile,
                                            @RequestParam(value = "smscode", required = true) String smscode) {
        return checkMobileSmscode(mobile, smscode);
    }

    /**
     * 已登录用户，根据token检查验证手机号
     * @param token
     * @param smscode
     *
     * @return
     */
    @RequestMapping(value = "/app/checkTokenSmscode", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> checkTokenSmscode(@RequestParam(value = "token", required = true) String token,
                                            @RequestParam(value = "smscode", required = true) String smscode) {
        String[] user = tokenService.getUserByToken(token);
        if (user == null) {
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        return checkMobileSmscode(user[1], smscode);
    }

    /**
     * 检查手机验证码
     *
     * @param mobile
     * @param smscode
     * @return
     */
    private Map<String, Object> checkMobileSmscode(String mobile, String smscode){
        String tokenSmsCode = (String) tokenService.get("SMS_" + mobile);
        if (!smscode.equalsIgnoreCase(tokenSmsCode)) {
            return CommonUtil.generateJsonMap("ER91", "输入验证码错误");
        }

        String authcode = CommonUtil.generateAuthCode();
        tokenService.put(authcode, mobile, 2);

        return CommonUtil.generateJsonMap("OK", authcode);
    }

    /**
     * 注册 - 输入密码
     *
     * @return
     */
    @RequestMapping(value = "/app/stepRegister", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> stepRegister(HttpServletRequest request,
            @RequestParam(value = "authcode", required = true) String authcode,
            @RequestParam(value = "pwd", required = true) String pwd) {
        String mobile = tokenService.get(authcode);
        if (mobile == null) {
            return CommonUtil.generateJsonMap("ER91", "输入参数错误");
        }

        LoanUser user = new LoanUser();
        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setLoginPwd(pwd);
        user.setRegisterTime(new Date());

        // 用户状态为正常
        user.setUserStatus(Constant.USER_STATUS_NORMAL);

        // 注册IP地址
        String host = CommonUtil.getRemoteHost(request);
        user.setRegisterIP(host);

        if (userService.save(user) > 0) {
            return CommonUtil.generateJsonMap("OK", null);
        }else{
            return CommonUtil.generateJsonMap("ER90", "注册失败");
        }
    }

    /**
     * 重置密码
     *
     * @return
     */
    @RequestMapping(value = "/app/resetPwd", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> resetPwd(HttpServletRequest request,
                                        @RequestParam(value = "authcode", required = true) String authcode,
                                        @RequestParam(value = "pwd", required = true) String pwd) {
        String mobile = tokenService.get(authcode);
        if (mobile == null) {
            return CommonUtil.generateJsonMap("ER91", "输入参数错误");
        }

        LoanUser user = userService.getUser(mobile);
        if (user == null){
            return CommonUtil.generateJsonMap("ER14", "用户不存在");
        }

        if (userService.updateLoginPwd(user.getUserId(), user.encryptPwd(pwd))){
            return CommonUtil.generateJsonMap("OK", null);
        }
        else{
            return CommonUtil.generateJsonMap("ER90", "重置密码失败");
        }
    }
}
