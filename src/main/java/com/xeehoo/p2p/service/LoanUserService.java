package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.LoanUserFund;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * Created by wangzunhui on 2015/9/28.
 */
public interface LoanUserService {
    /**
     * 保存用户信息
     *
     * @param userInfo
     * @return
     */
    public Integer save(LoanUser userInfo);

    /**
     * 用户登录
     *
     * @param loginName
     * @param loginPwd
     * @param loginType
     * @return
     */
    public Optional<LoanUser> login(String loginType, String loginName, String loginPwd);

    /**
     * 修改密码
     *
     * @param UserID
     * @param oldLoginPwd
     * @param newLoginPwd
     * @return
     */
    public boolean changeLoginPwd(Integer UserID, String oldLoginPwd, String newLoginPwd);

    /**
     * 更改支付密码
     *
     * @param userId
     * @param oldPayPwd
     * @param newPayPwd
     * @return
     */
    public boolean changePayPwd(Integer userId, String oldPayPwd, String newPayPwd);

    /**
     * 设置支付密码
     *
     * @param userId
     * @param payPwd
     * @return
     */
    public boolean updatePayPwd(Integer userId, String payPwd);

    /**
     * 根据（登录名、手机号）检查用户是否存在
     *
     * @param name
     * @param checkType
     * @return
     */
    public boolean checkUser(String checkType, String name);

    /**
     * 获取用户资产状况
     *
     * @param userID
     * @return
     */
    public LoanUserFund getFundByUserID(Integer userID);

    /**
     *
     * @param user
     * @return
     */
    public Integer updateUserAuthentication(LoanUser user);

    /**
     * 根据用户名或手机号查询用户信息
     *
     * @param loginName
     * @return
     */
    public LoanUser getUser(String loginName);

    /**
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getUserPager(int page, Map<String, Object> cond);
}
