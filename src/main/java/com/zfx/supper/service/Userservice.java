package com.zfx.supper.service;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysUser;

public interface Userservice {
    SysUser getUserByName(String username);

    Results<SysUser> getAllUserByPage(Integer offset, Integer limit);
}
