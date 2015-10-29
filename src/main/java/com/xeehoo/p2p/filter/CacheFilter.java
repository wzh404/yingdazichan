package com.xeehoo.p2p.filter;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wangzunhui on 2015/10/28.
 */
@Component
public class CacheFilter implements Filter, ApplicationContextAware {
    private final org.apache.log4j.Logger logger = Logger.getLogger(CacheFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("--------------filter--------------" + ((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
