package com.xeehoo.p2p.cache;

/**
 * Created by wangzunhui on 2015/12/2.
 */
public interface Cache {
    /**
     *
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     *
     * @param key
     * @param val
     */
    public void set(String key, Object val);

    /**
     *
     * @param key
     * @param val
     * @param expire
     */
    public void set(String key, Object val, long expire);

    /**
     *
     * @param key
     */
    public void remove(String key);
}
