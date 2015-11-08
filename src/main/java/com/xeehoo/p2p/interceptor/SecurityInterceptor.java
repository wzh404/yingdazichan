package com.xeehoo.p2p.interceptor;

import com.xeehoo.p2p.annotation.Permission;
import com.xeehoo.p2p.po.LoanPermission;
import com.xeehoo.p2p.po.LoanStaff;
import com.xeehoo.p2p.po.StaffSessionObject;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by wzh404@hotmail.com on 2015/10/31.
 */
public class SecurityInterceptor implements HandlerInterceptor {
    private final Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("staff") == null){
            logger.error("request session is null or no staff attribute!");
            response.sendRedirect("/staff");
            return false;
        }

        StaffSessionObject sso = (StaffSessionObject)session.getAttribute("staff");
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            Permission perm = method.getDeclaredAnnotation(Permission.class);
            if (perm == null){
                logger.error("invalid method '" + method.getName() + "', has not annotation.");
                response.sendRedirect("/error");
                return false;
            }

            if (!sso.isAuth(perm.value())){
                logger.error("no authorize to request");
                response.sendRedirect("/error");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
