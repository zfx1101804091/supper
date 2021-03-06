package com.zfx.supper.service;

import com.alibaba.fastjson.JSONArray;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysPermission;

public interface PermissionService {


    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer roleId);

    Results<SysPermission> getMenuAll();

    Results<SysPermission> getMenu(Long userId);

    Results<SysPermission> save(SysPermission sysPermission);

    SysPermission getSysPermissionById(Integer id);

    Results updateSysPermission(SysPermission sysPermission);

    Results delete(Integer id);
}
