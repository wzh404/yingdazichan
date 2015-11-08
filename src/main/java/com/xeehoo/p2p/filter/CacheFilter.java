package com.xeehoo.p2p.filter;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangzunhui on 2015/10/28.
 */
@Component
public class CacheFilter implements Filter{
    private final org.apache.log4j.Logger logger = Logger.getLogger(CacheFilter.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (uri.startsWith("/cache")) {
            String value = (String)redisTemplate.opsForValue().get(uri);
            String flush = request.getParameter("flush");
            if (value == null || "true".equalsIgnoreCase(flush)) {//刷新缓存
                ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);
                chain.doFilter(request, wrapper);
                value = wrapper.getResult();
                if (!StringUtils.isEmpty(value)){
                    redisTemplate.opsForValue().set(uri, value);
                }

                logger.info("cache miss: " + value);
            } else {
                logger.info("cache hit: " + value);
            }
            response.getWriter().print(value);
        }
        else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
