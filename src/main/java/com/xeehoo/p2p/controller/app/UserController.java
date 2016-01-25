package com.xeehoo.p2p.controller.app;

import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.service.LoanInvestService;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.service.TokenService;
import com.xeehoo.p2p.util.CommonUtil;
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
    private LoanInvestService investService;

    @Autowired
    private TokenService tokenService;

    /**
     * APP登录
     *
     * @param request
     * @param name  登录
     * @param pwd  密码
     * @return
     */
    @RequestMapping(value = "/app/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "pwd", required = true) String pwd) {
        LoanUser user = userService.getUser(name);
        if (user == null){
            return CommonUtil.generateJsonMap("ER10", "用户不存在");
        }

        if (user.isEqualPwd(pwd)) {
            String token = TokenUtil.generateAuthorizationToken(name);
            tokenService.put(token, user.getUserId().toString() + "," + name, 10);

            return CommonUtil.generateJsonMap("OK", token);
        }
        else{
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
        if (userId == null){
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        if (userService.changeLoginPwd(userId, oldLoginPwd, newLoginPwd)){
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
        if (userId == null){
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        if (userService.changePayPwd(userId, oldLoginPwd, newLoginPwd)){
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
        Integer userId = tokenService.getUserId(token);
        if (userId == null){
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        String tokenSmsCode = (String)tokenService.get("SMS_" + token);
        if (!smscode.equalsIgnoreCase(tokenSmsCode)){
            return CommonUtil.generateJsonMap("ER91", "输入验证码错误");
        }

        if (userService.updatePayPwd(userId, newLoginPwd)){
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
    public Map<String, Object> sendSms(@RequestParam(value = "token", required = true) String token){
        String[] user = tokenService.getUserByToken(token);
        if (user == null){
            return CommonUtil.generateJsonMap("ER90", "非法参数,请重新登录");
        }

        String smscode = CommonUtil.generateAuthCode();
        logger.info(user[1] + " smscode is " + smscode);
        tokenService.put("SMS_" + token, smscode, 5);

        return CommonUtil.generateJsonMap("OK", null);
    }
}
