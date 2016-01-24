package com.xeehoo.p2p.service;

import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanStaff;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/14.
 */
public interface LoanStaffService {

    /**
     *
     * @param staffId
     * @return
     */
    public LoanStaff getStaff(Integer staffId);

    /**
     *
     * @param staffLogin
     * @return
     */
    public LoanStaff getStaffByLogin(String staffLogin);


    /**
     * 新增职员
     *
     * @param staff
     * @return
     */
    public Integer saveStaff(LoanStaff staff);

    /**
     * 修改职员信息
     *
     * @param staff
     * @return
     */
    public Integer updateStaff(LoanStaff staff);

    /**
     * 管理员登录
     *
     * @param staffLogin
     * @param staffPwd
     * @return
     */
    public Map<String, Object> staffLogin(String staffLogin, String staffPwd);

    /**
     * 修改职员状态
     *
     * @param staffId
     * @param staffStatus
     * @return
     */
    public Integer changeStaffStatus(Integer staffId, Integer staffStatus);

    /**
     * 修改职员密码
     *
     * @param staffId
     * @param staffPwd
     * @return
     */
    public Integer changeStaffPwd(Integer staffId, String staffPwd);

    /**
     * 列表职员
     *
     * @param page
     * @param cond
     * @return
     */
    public LoanPagedListHolder getStaffPager(int page, QueryCondition cond);
}
