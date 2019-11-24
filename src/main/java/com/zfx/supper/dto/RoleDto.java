package com.zfx.supper.dto;

import com.zfx.supper.model.SysRole;

import java.util.List;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/24 0024 10:23
 */
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
