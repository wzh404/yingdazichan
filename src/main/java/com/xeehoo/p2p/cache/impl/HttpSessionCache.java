package com.xeehoo.p2p.cache.impl;

import com.xeehoo.p2p.cache.Cache;

import javax.servlet.http.HttpSession;

/**
 * Created by wangzunhui on 2015/12/2.
 */
public class HttpSessionCache implements Cache {
    HttpSession session = null;

    public HttpSessionCache(HttpSession session){
        this.session = session;
    }

    @Override
    public Object get(String key) {
        if (session == null)
            return null;

        return session.getAttribute(key);
    }

    @Override
    public void set(String key, Object val) {
        if (session == null)
            return;

        session.setAttribute(key, val);
    }

    @Override
    public void set(String key, Object val, long expire) {
        if (session == null)
            return;

        session.setAttribute(key, val);
        session.setMaxInactiveInterval((int) expire);
    }

    @Override
    public void remove(String key) {
        if (session == null)
            return;

        session.removeAttribute(key);
    }
}
