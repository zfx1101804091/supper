package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.mapper.RoleMapper;
import com.zfx.supper.mapper.RolePermissionMapper;
import com.zfx.supper.model.SysRole;
import com.zfx.supper.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    
    @Override
    public Results<SysRole> getAllRole() {
        return Results.success(roleMapper.countAllRoles(),roleMapper.getAllRole());
    }

    @Override
    public Results<SysRole> saveRole(RoleDto roleDto) {
        //1.先保存角色
        roleMapper.saveRole(roleDto);
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除0，permission id是从1开始
        permissionIds.remove(0L);
        //2.保存角色对应的所有权限
        if(!CollectionUtils.isEmpty(permissionIds)){
            rolePermissionMapper.save(roleDto.getId(),permissionIds);
        }
        return Results.success();
    }

    @Override
    public int updateRole(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //1.更新角色权限之前要删除该角色之前的所有权限
        rolePermissionMapper.deleteRolePermission(roleDto.getId());
        //2.判断该角色是否有赋予权限，有就添加
        if(!CollectionUtils.isEmpty(permissionIds)){
            rolePermissionMapper.save(roleDto.getId(),permissionIds);
        }
        return roleMapper.update(roleDto);
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    public Results<SysRole> getAllRoleByPage(Integer offset, Integer limit) {
        
        return Results.success(roleMapper.countAllRoles(),roleMapper.getAllRoleByPage(offset,limit));
    }

    @Override
    public Results<SysRole> findRoleByFuzzyRoleName(Integer offset, Integer limit,String rolename) {

        return Results.success(roleMapper.countAllRolesByName(rolename),roleMapper.findRoleByFuzzyRoleName(offset,limit,rolename));
    }

   
}
