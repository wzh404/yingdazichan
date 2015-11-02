package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanBulletin;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public interface LoanStaffService {
    /**
     * 管理员登录
     *
     * @param staffName
     * @param staffPwd
     * @return
     */
    public Map<String, Object> staffLogin(String staffName, String staffPwd);
}
