package com.zfx.supper.service;

import com.zfx.supper.model.SysUser;

public interface Userservice {
    SysUser getUserByName(String username);
}
