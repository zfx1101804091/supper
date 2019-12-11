package com.zfx.supper.controller;

import com.alibaba.fastjson.JSON;
import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.model.SysRole;
import com.zfx.supper.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    @GetMapping("all")
    @ResponseBody
    public Results<SysRole> all(){
        
        return roleService.getAllRole();
    }

    /*
     * 功能描述: 角色查询+分页排序
     *
     * @Param: []
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysRole>
     * @Author: Administrator
     * @Date: 2019/11/22 0022 21:56
     */
    @RequestMapping("/list")
    @ResponseBody
    public Results<SysRole> list(PageTableRequest pageTable) {

        pageTable.countOffset();

        Results<SysRole> rolelist = roleService.getAllRoleByPage(pageTable.getOffset(), pageTable.getLimit());

        log.debug("role列表---{}",JSON.toJSONString(rolelist));
        
        return rolelist;
    }

    @RequestMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest pageTable,String rolename) {

        pageTable.countOffset();

        Results<SysRole> rolelist = roleService.findRoleByFuzzyRoleName(pageTable.getOffset(), pageTable.getLimit(),rolename);

        log.debug("rolename: {} >>>>> role列表---{}",rolename,JSON.toJSONString(rolelist));

        return rolelist;
    }

    @GetMapping(value = "/add")
    public String addRole(Model model) {
        model.addAttribute("sysRole",new SysRole());
        return "role/role-add";
    }
    
    @PostMapping(value = "/add")
    @ResponseBody
    public Results saveRole(@RequestBody RoleDto roleDto) {
        
        return roleService.saveRole(roleDto);
    }


    @GetMapping(value = "/edit")
    public String editRole(Model model,SysRole sysRole) {
        model.addAttribute("sysRole",roleService.getRoleById(sysRole.getId()));
        return "role/role-edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Results updateRole(@RequestBody RoleDto roleDto) {
        int a = roleService.updateRole(roleDto);
        return Results.success();
    }


    @GetMapping(value = "/delete")
    @ResponseBody
    public Results<SysRole> deleteRole(RoleDto roleDto) {
        return roleService.delete(roleDto.getId());
    }
}
