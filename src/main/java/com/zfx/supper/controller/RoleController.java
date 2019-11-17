package com.zfx.supper.controller;

import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysRole;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @RequestMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll(){

        Results<SysRole> allRoles = roleService.getAllRoles();
        log.debug("获取所有的角色名称---{}",allRoles);
        return allRoles;
    }


}
