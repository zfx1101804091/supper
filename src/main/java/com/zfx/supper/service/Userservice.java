package com.zfx.supper.service;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.UserDto;
import com.zfx.supper.model.SysUser;

public interface Userservice {
    
    SysUser getUserByName(String username);

    /*
     * 功能描述: 用户分页
     * 
     * @Param: [offset, limit]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysUser>
     * @Author: Administrator
     * @Date: 2019/11/18 0018 1:45
     */
    Results<SysUser> getAllUserByPage(Integer offset, Integer limit);

    /*
     * 功能描述: 用户保存
     * 
     * @Param: [userDto, roleId]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysUser>
     * @Author: Administrator
     * @Date: 2019/11/18 0018 1:45
     */
    Results<SysUser> save(SysUser sysUser, Integer roleId);

    SysUser getUserByPhone(String telephone);

    SysUser getUserById(Long id);

    Results<SysUser> updateUser(UserDto userDto, Integer roleId);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Long id);

    Results<SysUser> findUserByFuzzyUserName(Integer offset, Integer limit, String username);
}
