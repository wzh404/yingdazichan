package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.UserMapper;
import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.po.LoanUserFund;
import com.xeehoo.p2p.service.LoanUserService;
import com.xeehoo.p2p.util.CommonUtil;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.EncryptUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by wangzunhui on 2015/9/28.
 */
@Service("userService")
public class LoanUserServiceImpl implements LoanUserService {
    private final Logger logger = Logger.getLogger(LoanUserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer save(LoanUser userInfo) {
        java.util.Date regtime = new java.util.Date(System.currentTimeMillis());
        userInfo.setRegisterTime(regtime);
        userInfo.setLoginPwd(userInfo.encryptPwd(userInfo.getLoginPwd()));
        userInfo.setUserStatus(Constant.USER_STATUS_NORMAL);
        userMapper.saveUser(userInfo);

        return userInfo.getUserId();
    }

    @Override
    public Optional<LoanUser> login(String loginType, String loginName, String loginPwd) {
        if (StringUtils.isEmpty(loginName) ||
            StringUtils.isEmpty(loginPwd) ||
            StringUtils.isEmpty(loginType)) {
            return Optional.empty();
        }

        LoanUser userInfo = userMapper.getUserLoginInfo(loginType, loginName);
        if (userInfo == null || !userInfo.isNormal()) // 用户不存在
            return Optional.empty();

        if (userInfo.isEqualPwd(loginPwd)){
            userInfo.setUserStatus(Constant.LOGIN_OK);
        }
        else{ // 密码不正确
            userInfo.setUserStatus(Constant.LOGIN_INVALID_PWD);
        }

        return Optional.of(userInfo);
    }

    @Override
    public boolean changeLoginPwd(Integer userId, String oldLoginPwd, String newLoginPwd) {
        LoanUser userInfo = userMapper.getUser(userId);
        if (userInfo != null){ // 用户不存在
            if (userInfo.isEqualPwd(oldLoginPwd)){
                String newEncryptLoginPwd = userInfo.encryptPwd(newLoginPwd);
                userMapper.updateUserLoginPwd(newEncryptLoginPwd, userId);

                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String checkType, String name){
        if (StringUtils.isEmpty(name) ||
            StringUtils.isEmpty(checkType)) {
            return false;
        }

        LoanUser userInfo = userMapper.getUserLoginInfo(checkType, name);
        if (userInfo == null){
            return false;
        }

        return true;
    }

    @Override
    public LoanUserFund getFundByUserID(Integer userId) {
        LoanUserFund userFund = userMapper.getUserFund(userId);
        if (userFund == null){
            userFund = new LoanUserFund();
            userFund.setUserId(userId);
            userFund.setAwaitEarnings(new BigDecimal(0.00f));
            userFund.setDynamicEarnings(new BigDecimal(0.00f));
            userFund.setTotalAssets(new BigDecimal(0.00f));
            userFund.setNotDueAmount(new BigDecimal(0.00f));
            userFund.setTotalEarnings(new BigDecimal(0.00f));
        }

        return userFund;
    }
}
