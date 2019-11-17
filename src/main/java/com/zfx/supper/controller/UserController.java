package com.zfx.supper.controller;

import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/14 0014 22:43
 */
@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private Userservice userservice;
    
    @RequestMapping("/getUser")
    public String getUser(Model model){
      
        return "admin-list";
    }
    
    @RequestMapping("/{username}")
    @ResponseBody
    public SysUser getUserByName(@PathVariable String username){
        SysUser user = userservice.getUserByName(username);
        
        log.debug("UserController.getUserByName()--response:{}",user.toString());
        return user;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Results<SysUser> list(PageTableRequest pageTable){

        pageTable.countOffset();
      
        return userservice.getAllUserByPage(pageTable.getOffset(),pageTable.getLimit());
    }

    /*
     * 功能描述: 新增用户信息
     * 
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2019/11/17 0017 23:03
     */
    @RequestMapping("/add")
    public String addUser(Model model){
        //初始化SysUser，防止前端thymeleaf取值时报异常 th:value="${sysUser.sex}"
        model.addAttribute(new SysUser());
        return "user/user-add";
    }
}
