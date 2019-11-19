package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.UserDto;
import com.zfx.supper.mapper.RoleUserMapper;
import com.zfx.supper.mapper.UserMapper;
import com.zfx.supper.model.SysRoleUser;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/17 0017 01:10
 */
@Service
@Transactional
public class UserserviceImpl implements Userservice {
    
    @Autowired
    public UserMapper userMapper;
    
    @Autowired
    public RoleUserMapper roleUserMapper;
    
    @Override
    public SysUser getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public Results<SysUser> getAllUserByPage(Integer offset, Integer limit) {
        return Results.success(userMapper.countAllUsers().intValue(),userMapper.getAllUsersByPage(offset,limit));
    }

    @Override
    public Results<SysUser> save(SysUser sysUser, Integer roleId) {

        if(roleId!=null){
            userMapper.save(sysUser);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(sysUser.getId().intValue());
            
            roleUserMapper.save(sysRoleUser);
            return Results.success();
        }
        return Results.failure();
    }

    @Override
    public SysUser getUserByPhone(String telephone) {
        
        return userMapper.getUserByPhone(telephone);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {
        
        if(roleId != null){
            userMapper.updateUser(userDto);
            SysRoleUser sysRoleUser = new SysRoleUser();
            
            sysRoleUser.setUserId(userDto.getId().intValue());
            sysRoleUser.setRoleId(roleId);
            if(roleUserMapper.getSysRoleUserByUserId(userDto.getId().intValue())!= null){
                roleUserMapper.updateSysRoleUser(sysRoleUser);
            }else{
                roleUserMapper.save(sysRoleUser);
            }
            return Results.success();
        }else{
            return Results.failure();
        }
    }
}
