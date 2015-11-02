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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        map.put("result", Constant.RESULT_ERROR);
        map.put("resultMsg", "用户不存在");

        LoanStaff loanStaff = staffMapper.getStaffByName(staffName);
        Optional.ofNullable(loanStaff)
            .filter((staff) -> {
                logger.info("---------check password--------");
                return staff.getStaffPwd().equalsIgnoreCase(staffPwd);
            })
            .map((staff) -> {
                logger.info("-------check permission----");
                List<LoanPermission> perms = permissionMapper.getPermissionByRoleCode(staff.getStaffRole());
                return perms;
            })
            .ifPresent((perms) -> {
                for (LoanPermission perm : perms) {

                };
            });
        logger.info("---------return--------");
        return map;
    }
}
