package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanBulletin;
import com.xeehoo.p2p.po.LoanStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/10/24.
 */
public interface StaffMapper {
    /**
     * 根据用户名称查询用户信息
     *
     * @param staffId
     * @return
     */
    public LoanStaff getStaff(@Param("staff_id")Integer staffId);

    /**
     * 根据用户名称查询用户信息
     *
     * @param staffLogin
     * @return
     */
    public LoanStaff getStaffByLogin(@Param("staffLogin")String staffLogin);

    /**
     * 新增员工
     *
     * @param staff
     * @return
     */
    public Integer saveStaff(LoanStaff staff);

    /**
     *
     * @param staff
     * @return
     */
    public Integer updateStaff(LoanStaff staff);

    /**
     * 修改员工状态
     *
     * @param staffId
     * @param staffStatus
     * @return
     */
    public Integer changeStaffStatus(@Param("staffId")Integer staffId, @Param("staffStatus")Integer staffStatus);

    /**
     * 修改员工密码
     *
     * @param staffId
     * @param staffPwd
     * @return
     */
    public Integer changeStaffPwd(@Param("staffId")Integer staffId, @Param("staffPwd")String staffPwd);

    /**
     *
     * @param cond
     * @return
     */
    public List<LoanStaff> getStaffs(Map<String, Object> cond);

    /**
     *
     * @param cond
     * @return
     */
    public Integer getTotalStaff(Map<String, Object> cond);
}
