package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.mybatis.mapper.PermissionMapper;
import com.xeehoo.p2p.po.LoanRole;
import com.xeehoo.p2p.service.LoanPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WIN10 on 2016/1/23.
 */
@Service("permissionService")
public class LoanPermissionServiceImpl implements LoanPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<LoanRole> getRoles() {
        return permissionMapper.getRoles();
    }
}
