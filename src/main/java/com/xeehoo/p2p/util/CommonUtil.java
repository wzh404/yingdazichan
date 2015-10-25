package com.xeehoo.p2p.util;

import com.xeehoo.p2p.po.SessionObject;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by wangzunhui on 2015/9/28.
 */
public class CommonUtil {
    private static final String ENCRYPT_KEY = "!@%6*He(3HJ7$9dsFGK^";

    /**
     * 生成用户登录token
     *
     * @param loginName
     * @param loginIP
     * @return
     */
    public static String generateToken(String loginName, String loginIP){
        long times = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(loginName);
        sb.append(times);
        sb.append(loginIP);
        sb.append(CommonUtil.ENCRYPT_KEY);

        try {
            String token = EncryptUtil.getSignature(sb.toString(), CommonUtil.ENCRYPT_KEY);
            return token;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String generateAuthCode(){
        Integer r = (int)((Math.random() * 9 + 1) * 100000);
        return r.toString();
    }

    /**
     * 生成LONG型随机数
     *
     * @return
     */
    public static long random(){
        try {
            return SecureRandom.getInstance("SHA1PRNG").nextLong();
        }catch (Exception e){
            Random r = new Random(System.currentTimeMillis());

            return r.nextLong();
        }
    }

    /**
     * 加密密码
     *
     * @param key
     * @param pwd
     * @return

    public static String generatePwd(String key, String pwd){
        StringBuilder sb = new StringBuilder(pwd);
        sb.append(key);
        sb.append(CommonUtil.ENCRYPT_KEY);
        String encPwd = DigestUtils.md5DigestAsHex(sb.toString().getBytes());

        return encPwd;
    }*/

    /**
     *
     * @param resultCode
     * @param resultMsg
     * @return
     */
    public static Map<String, Object> generateJsonMap(String resultCode, String resultMsg){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultCode", resultCode);
        if (resultMsg != null) {
            map.put("resultMsg", resultMsg);
        }

        return map;
    }

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip)){
            return "127.0.0.1";
        }

        if (ip != null){
            String[] host = ip.split(",");
            return host[0];
        }

        return "0.0.0.0";
    }

    /**
     * 创建返回错误消息的ModelAndView
     *
     * @param viewName
     * @param errorMsg
     * @return
     */
    public static ModelAndView createErrorModelAndView(String viewName, String errorMsg){
        Map<String, String> map = new HashMap<String, String>();
        map.put("errorMsg", errorMsg);
        return new ModelAndView(viewName, map);
    }

    /**
     *
     * @param cache
     * @param token
     * @return
     */
    public static SessionObject getSessionObject(Object cache, String token){
        if (cache instanceof HttpServletRequest){
            HttpSession session = ((HttpServletRequest)cache).getSession();
            return (SessionObject)session.getAttribute(Constant.SESSION_USER_LOGIN);
        }

        return null;
    }

    /**
     *
     * @param cache
     * @param token
     * @param so
     */
    public static void setSessionObject(Object cache, String token, SessionObject so){
        if (cache instanceof HttpSession){
//            HttpSession session = ((HttpServletRequest)cache).getSession(true);
            HttpSession session = (HttpSession)cache;
            session.setAttribute(Constant.SESSION_USER_LOGIN, so);
            session.setMaxInactiveInterval(20 * 60); // 20分钟未交互
        }
    }
}
