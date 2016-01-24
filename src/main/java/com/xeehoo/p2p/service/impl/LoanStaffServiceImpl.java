package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.PermissionMapper;
import com.xeehoo.p2p.mybatis.mapper.StaffMapper;
import com.xeehoo.p2p.po.LoanPermission;
import com.xeehoo.p2p.po.LoanStaff;
import com.xeehoo.p2p.po.LoanUserRepay;
import com.xeehoo.p2p.po.StaffSessionObject;
import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.util.Constant;
import com.xeehoo.p2p.util.LoanPagedListHolder;
import com.xeehoo.p2p.util.QueryCondition;
import com.xeehoo.p2p.util.QueryPager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangzunhui on 2015/11/2.
 */
@Service
public class LoanStaffServiceImpl implements LoanStaffService {
    private final Logger logger = Logger.getLogger(LoanStaffServiceImpl.class);

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public LoanStaff getStaff(Integer staffId) {
        return staffMapper.getStaff(staffId);
    }

    @Override
    public LoanStaff getStaffByLogin(String staffLogin) {
        return staffMapper.getStaffByLogin(staffLogin);
    }

    @Override
    public Integer saveStaff(LoanStaff staff) {
        return staffMapper.saveStaff(staff);
    }

    @Override
    public Integer updateStaff(LoanStaff staff) {
        return staffMapper.updateStaff(staff);
    }

    @Override
    public Map<String, Object> staffLogin(String staffLogin, String staffPwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        StaffSessionObject sso = new StaffSessionObject();

        map.put("resultCode", Constant.RESULT_ERROR);
        map.put("resultMsg", "用户不存在");

        LoanStaff loanStaff = staffMapper.getStaffByLogin(staffLogin);
        Optional.ofNullable(loanStaff)
                .filter((staff) -> {
                    logger.info("[" + staff.encryptPwd(staffPwd) + "]");
                    boolean isPwdOK = staff.isEqualPwd(staffPwd);
                    if (!isPwdOK) {
                        map.put("resultMsg", "用户密码错误");
                    }
                    return isPwdOK;
                })
                .map((staff) -> {
                    return permissionMapper.getPermissionByRoleCode(staff.getStaffRole());
                })
                .ifPresent((perms) -> {
                    map.put("resultCode", Constant.RESULT_OK);
                    sso.setStaffId(loanStaff.getStaffId());
                    sso.setStaffName(loanStaff.getStaffName());
                    sso.setStaffLogin(loanStaff.getStaffLogin());

//                    map.put("staff", loanStaff);
                    List<LoanPermission> permissions = perms.stream()
//                            .filter((perm) -> perm.getPermissionPid() > 0)
                            .collect(Collectors.toList());
                    for (LoanPermission perm : permissions)
                        logger.info(perm.getPermissionCode() + " - " + perm.getPermissionName());
//                    map.put("perm", rolePerms);
                    sso.setPermissions(permissions);

                    Map<String, List<LoanPermission>> menus = perms.stream()
                            .filter((perm) -> perm.getPermissionPid() == 0)
                            .collect(Collectors.groupingBy(LoanPermission::getMenuCode));
//                    map.put("menu", menu);
                    sso.setMenus(menus);
                    map.put("staff", sso);
                });
        return map;
    }

    @Override
    public Integer changeStaffStatus(Integer staffId, Integer staffStatus){
        return staffMapper.changeStaffStatus(staffId, staffStatus);
    }

    @Override
    public Integer changeStaffPwd(Integer staffId, String staffPwd){
        return staffMapper.changeStaffPwd(staffId, staffPwd);
    }

    @Override
    public LoanPagedListHolder getStaffPager(int page, QueryCondition cond) {
        return new QueryPager<LoanStaff>(page, cond) {
            @Override
            public Integer total(QueryCondition cond) {
                return staffMapper.getTotalStaff(cond.getCond());
            }

            @Override
            public List<LoanStaff> elements(int page, QueryCondition cond) {
                return staffMapper.getStaffs(cond.getCond());
            }
        }.query();
    }
}
