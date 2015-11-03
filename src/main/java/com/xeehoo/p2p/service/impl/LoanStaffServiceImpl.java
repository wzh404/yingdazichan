package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.PermissionMapper;
import com.xeehoo.p2p.mybatis.mapper.StaffMapper;
import com.xeehoo.p2p.po.LoanPermission;
import com.xeehoo.p2p.po.LoanStaff;
import com.xeehoo.p2p.service.LoanStaffService;
import com.xeehoo.p2p.util.Constant;
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
    public Map<String, Object> staffLogin(String staffName, String staffPwd) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultCode", Constant.RESULT_ERROR);
        map.put("resultMsg", "用户不存在");

        LoanStaff loanStaff = staffMapper.getStaffByName(staffName);
        Optional.ofNullable(loanStaff)
                .filter((staff) -> {
                    boolean isPwdOK = staff.getStaffPwd().equalsIgnoreCase(staffPwd);
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
                    map.put("staff", loanStaff);
                    List<LoanPermission> rolePerms = perms.stream()
                            .filter((perm) -> perm.getPermissionPid() > 0)
                            .collect(Collectors.toList());
                    for (LoanPermission perm : rolePerms)
                        logger.info(perm.getPermissionCode() + " - " + perm.getPermissionName());
                    map.put("perm", rolePerms);

                    Map<String, List<LoanPermission>> menu = perms.stream()
                            .filter((perm) -> perm.getPermissionPid() == 0)
                            .collect(Collectors.groupingBy(LoanPermission::getMenuCode));
                    map.put("menu", menu);
                });
        return map;
    }
}
