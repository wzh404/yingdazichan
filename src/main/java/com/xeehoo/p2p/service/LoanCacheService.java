package com.xeehoo.p2p.service;

/**
 * Created by wangzunhui on 2015/9/28.
 */
public interface LoanCacheService {
    /**
     * cache手机验证码
     *
     * @param mobile
     * @param authCode
     */
    public void setMobileAuthCode(String mobile, String authCode);

    /**
     * 获取cache的手机验证码
     *
     * @param mobile
     */
    public String getMobileAuthCode(String mobile);


    /**
     * 用户密码错误次数加1，5分钟后清0
     *
     * @param host
     * @return
     */
    public int addUserPwdErrorTimes(String host);

    /**
     * 获取用户密码错误次数
     *
     * @param host
     * @return
     */
    public int getUserPwdErrorTimes(String host);
}
