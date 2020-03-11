package com.zfx.supper.service.impl;

import com.zfx.supper.dto.LoginUser;
import com.zfx.supper.mapper.PermissionMapper;
import com.zfx.supper.model.SysPermission;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:    处理用户信息获取逻辑
 * @author: zheng-fx
 * @time: 2019/12/15 0015 17:05
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private Userservice userservice;
    
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        SysUser sysUser = userservice.getUserByName(username);
        if (sysUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (sysUser.getStatus() == SysUser.Status.LOCKED) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (sysUser.getStatus() == SysUser.Status.DISABLED) {
            throw new DisabledException("用户已作废");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);

        //查出用户角色权限，并赋值给用户
        List<SysPermission> permissions = permissionMapper.listByUserId(sysUser.getId());
        loginUser.setPermissions(permissions);

        return loginUser;
    }

}