package com.zfx.supper.service.impl;

import com.zfx.supper.base.result.ResponseCode;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.mapper.RoleMapper;
import com.zfx.supper.mapper.RolePermissionMapper;
import com.zfx.supper.mapper.RoleUserMapper;
import com.zfx.supper.model.SysRole;
import com.zfx.supper.model.SysRoleUser;
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
    @Autowired
    private RoleUserMapper roleUserMapper;
    
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
    public Results<SysRole> delete(Integer id) {
        /*考虑到角色删除后，其关联的用户相当于没有这个角色的权限了，该用户也应该删除，不然体验不好
            如：【角色删除后】该角色的用户登陆时，啥也看不到
            
            所以先查该角色关联的用户，
            如果有关联用户，则不删除，
            数据库设置RESTRICT：如果想要删除父表的记录时，而在子表中有关联该父表的记录，则不允许删除父表中的记录；
            但是该用户下的菜单权限可以删除；数据库设置CASCADE：级联删除
        */
        List<SysRoleUser> datas = roleUserMapper.listAllSysRoleUserByRoleId(id);
        if(datas.size() <= 0){
            roleMapper.delete(id);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());

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
