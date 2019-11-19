package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.mapper.RoleUserMapper;
import com.zfx.supper.model.SysRoleUser;
import com.zfx.supper.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public Results getSysRoleUserByUserId(Integer userId) {

        SysRoleUser sysRoleUser = roleUserMapper.getSysRoleUserByUserId(userId);
        if(sysRoleUser != null){
            return Results.success(sysRoleUser);
        }else{
            return Results.success();
        }
    }
}
