package com.zfx.supper.service.impl;

import com.zfx.supper.mapper.UserMapper;
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
    @Override
    public SysUser getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
