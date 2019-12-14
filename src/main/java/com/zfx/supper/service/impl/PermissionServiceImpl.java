package com.zfx.supper.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.mapper.PermissionMapper;
import com.zfx.supper.model.SysPermission;
import com.zfx.supper.service.PermissionService;
import com.zfx.supper.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Results<JSONArray> listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        
        List datas = permissionMapper.findAll();
        JSONArray array = new JSONArray();
        
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        
        //递归查询树节点+赋值
        TreeUtils.setPermissionsTree(0, datas, array);
        
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listByRoleId(Integer roleId) {
        List<SysPermission> datas = permissionMapper.listByRoleId(roleId);
        return Results.success(0,datas);
    }

    @Override
    public Results<SysPermission> getMenuAll() {
        return Results.success(0, permissionMapper.findAll());
    }

    @Override
    public Results getMenu(Long userId) {
        List<SysPermission> datas = permissionMapper.listByUserId(userId);
        datas = datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    @Override
    public Results save(SysPermission sysPermission) {
        return (permissionMapper.save(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return permissionMapper.getSysPermissionById(id);
    }

    @Override
    public Results updateSysPermission(SysPermission sysPermission) {
        return (permissionMapper.update(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(Integer id) {
        permissionMapper.deleteById(id);
        permissionMapper.deleteByParentId(id);
        return Results.success();
    }


}
