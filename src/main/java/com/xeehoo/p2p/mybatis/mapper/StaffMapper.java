package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanStaff;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/24.
 */
public interface StaffMapper {
    /**
     * 根据用户名称查询用户信息
     *
     * @param staffName
     * @return
     */
    public LoanStaff getStaffByName(String staffName);
}
