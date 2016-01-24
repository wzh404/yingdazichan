package com.xeehoo.p2p.mybatis.mapper;

import com.xeehoo.p2p.po.LoanPermission;
import com.xeehoo.p2p.po.LoanRole;
import com.xeehoo.p2p.po.LoanStaff;

import java.util.List;

/**
 * Created by wangzunhui on 2015/10/24.
 */
public interface PermissionMapper {
    /**
     * 根据用户角色查询权限
     *
     * @param roleCode
     * @return
     */
    public List<LoanPermission> getPermissionByRoleCode(String roleCode);

    /**
     * 查询角色
     *
     * @return
     */
    public List<LoanRole> getRoles();
}
