package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.UserDto;
import com.zfx.supper.mapper.RoleUserMapper;
import com.zfx.supper.mapper.UserMapper;
import com.zfx.supper.model.SysRoleUser;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public int deleteUser(Long id) {
        
        roleUserMapper.deleteRoleUserByUserId(id.intValue());
        return userMapper.deleteUser(id.intValue());
    }

    @Override
    public Results<SysUser> findUserByFuzzyUserName(Integer offset, Integer limit, String username) {
        return Results.success(userMapper.findUserByFuzzyUserName(username).intValue(),userMapper.findUserByFuzzyUserNameByPage(offset,limit,username));
    }

    @Override
    public Results<SysUser> changePassword(String username, String oldPassword, String newPassword) {
        SysUser u = userMapper.getUser(username);
        /*
        密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），
        而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，
        然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确。
         */
        boolean matches = new BCryptPasswordEncoder().matches(oldPassword, u.getPassword());
        if (u == null) {
            return Results.failure(1,"用户不存在");
        }else if (!matches) {
            System.out.println("u.getPassword()----"+u.getPassword());
            return Results.failure(1,"旧密码错误,请重新修改");
        }else {
            userMapper.changePassword(u.getId(), new BCryptPasswordEncoder().encode(newPassword));
            return Results.success();
        }
    }

}
