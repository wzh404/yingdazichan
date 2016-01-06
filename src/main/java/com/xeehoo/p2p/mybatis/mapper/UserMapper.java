package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.LoanUserFund;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Vinnie.Song on 2015/10/18.
 */
public interface UserMapper {
    /**
     * 用户注册
     *
     * @param userinfo 用户信息
     * @return 插入行数
     */
    public void saveUser(LoanUser userinfo);

    /**
     *
     * @param userId
     * @return
     */
    public LoanUser getUser(Integer userId);

    /**
     * 保存实名认证
     *
     * @param map
     * @return
     */
    public int updateUserIdCard(Map<String, Object> map);

    /**
     * 根据用户名或手机号查询用户信息
     *
     * @param loginType name or mobile
     * @param loginName
     * @return
     */
    public LoanUser getUserLoginInfo(@Param("loginType")String loginType, @Param("loginName")String loginName);

    /**
     * 修改登录密码
     *
     * @param loginPwd
     * @param userId
     * @return
     */
    public int updateUserLoginPwd(@Param("loginPwd")String loginPwd, @Param("userId") Integer userId);

    /**
     * 修改支付密码
     *
     * @param loginPwd
     * @param userId
     * @return
     */
    public int updateUserPayPwd(@Param("payPwd")String loginPwd, @Param("userId") Integer userId);

    /**
     * 获取用户资产信息
     *
     * @param userId
     * @return
     */
    public LoanUserFund getUserFund(Integer userId);

    /**
     *
     * @param user
     * @return
     */
    public Integer updateUserAuthentication(LoanUser user);
}
