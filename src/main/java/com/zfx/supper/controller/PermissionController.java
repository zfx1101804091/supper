package com.zfx.supper.controller;

import com.alibaba.fastjson.JSONArray;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.model.SysPermission;
import com.zfx.supper.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    /*
     * 功能描述: 权限树列表
     * 
     * @Param: []
     * @Return: com.zfx.supper.base.result.Results<com.alibaba.fastjson.JSONArray>
     * @Author: Administrator
     * @Date: 2019/11/24 0024 9:51
     */
    @RequestMapping(value = "listAllPermission",method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission(){
        
        return permissionService.listAllPermission();
    }

    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto) {
        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());
    }

}
