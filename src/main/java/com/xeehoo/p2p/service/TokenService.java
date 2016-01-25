package com.xeehoo.p2p.service;

/**
 * Created by wangzunhui on 2016/1/25.
 */
public interface TokenService {
    /**
     * 保存token到cache
     *
     * @param token
     * @param val
     */
    public void put(String token, String val);

    /**
     *
     * @param token
     * @param val
     * @param minutes
     */
    public void put(String token, String val, int minutes);

    /**
     * 从token中取出用户信息(userId, mobile)
     *
     * @param token
     * @return
     */
    public String[] getUserByToken(String token);

    /**
     * 从token中取出用户ID
     *
     * @param token
     * @return
     */
    public Integer getUserId(String token);

    /**
     * 从cache中取出token数据
     *
     * @param token
     * @return
     */
    public String get(String token);

}
