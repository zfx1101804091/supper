package com.zfx.supper.controller;

import com.alibaba.fastjson.JSON;
import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysRole;
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
    public String list(PageTableRequest pageTable) {

        pageTable.countOffset();

        Results<SysRole> rolelist = roleService.getAllRoleByPage(pageTable.getOffset(), pageTable.getLimit());

        String jsonString = JSON.toJSONString(rolelist);

        log.debug("role列表---{}",jsonString);
        
        return jsonString;
    }

    @RequestMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public String findRoleByFuzzyRoleName(PageTableRequest pageTable,String rolename) {

        pageTable.countOffset();

        Results<SysRole> rolelist = roleService.findRoleByFuzzyRoleName(pageTable.getOffset(), pageTable.getLimit(),rolename);

        String jsonString = JSON.toJSONString(rolelist);

        log.debug("rolename: {} >>>>> role列表---{}",rolename,jsonString);

        return jsonString;
    }

}
