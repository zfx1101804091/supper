package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.mapper.RoleMapper;
import com.zfx.supper.model.SysRole;
import com.zfx.supper.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public Results<SysRole> getAllRole() {
        return Results.success(roleMapper.countAllRoles(),roleMapper.getAllRole());
    }

    @Override
    public Results<SysRole> getAllRoleByPage(Integer offset, Integer limit) {
        
        return Results.success(roleMapper.countAllRoles(),roleMapper.getAllRoleByPage(offset,limit));
    }

    @Override
    public Results<SysRole> findRoleByFuzzyRoleName(Integer offset, Integer limit,String rolename) {

        return Results.success(roleMapper.countAllRolesByName(rolename),roleMapper.findRoleByFuzzyRoleName(offset,limit,rolename));
    }

   
}
