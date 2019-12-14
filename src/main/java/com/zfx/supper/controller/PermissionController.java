package com.zfx.supper.controller;

import com.alibaba.fastjson.JSONArray;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.model.SysPermission;
import com.zfx.supper.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/menuAll")
    @ResponseBody
    public Results getMenuAll(){
        return permissionService.getMenuAll();
    }

    /*
     * 功能描述: 动态菜单
     * 
     * @Param: [userId]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysPermission>
     * @Author: Administrator
     * @Date: 2019/12/14 0014 23:26
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> getMenu(Long userId) {
        return permissionService.getMenu(userId);
    }

    /*
     * 功能描述: 新增菜单跳转
     *  
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/12/14 0014 15:58
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPermission(Model model) {
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    /*
     * 功能描述: 菜单新增
     * 
     * @Param: [permission]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysPermission>
     * @Author: Administrator
     * @Date: 2019/12/14 0014 15:59
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Results<SysPermission> savePermission(@RequestBody SysPermission permission) {
        log.debug("savePermission---permission--{}",permission);
        return permissionService.save(permission);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPermission(Model model, SysPermission permission) {
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Results updatePermission(@RequestBody  SysPermission permission) {
        return permissionService.updateSysPermission(permission);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Results deletePermission(SysPermission sysPermission) {
        return permissionService.delete(sysPermission.getId());
    }

}
