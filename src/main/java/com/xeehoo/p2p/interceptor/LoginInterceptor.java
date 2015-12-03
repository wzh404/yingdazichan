package com.xeehoo.p2p.interceptor;

import com.xeehoo.p2p.po.SessionObject;
import com.xeehoo.p2p.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangzunhui on 2015/10/10.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("client ip :" + CommonUtil.getRemoteHost(request));

        // 取session存取的用户信息
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("/user/login.jsp");
            return false;
        }

        SessionObject so = (SessionObject) session.getAttribute("user");
        if (so == null) {
            response.sendRedirect("/user/login.jsp");
            return false;
        }

        // 根据用户信息生成token
        String host = CommonUtil.getRemoteHost(request);
        logger.info(so.getLoginName() + " - " + host);
        String token = CommonUtil.generateToken(so.getLoginName(), host);
        logger.info("server-side-token: " + token);

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            logger.info("method: " + hm.getMethod().getName());
        }

        // 取客户端token
        String requestToken = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            logger.info("COOKIE: " + cookie.getName() + " - " + cookie.getValue());
            if (cookie.getName().equals("token")) {
                requestToken = cookie.getValue();
            }
        }

        // 比较客户端token与服务端生成的token
//        if (!requestToken.equals(token)) {
//            response.sendRedirect("/index.jsp");
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
