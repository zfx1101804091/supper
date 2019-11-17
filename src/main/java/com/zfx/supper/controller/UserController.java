package com.zfx.supper.controller;

import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.UserDto;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import com.zfx.supper.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.jws.WebResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @PostMapping("/add")
    @ResponseBody
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId){
        userDto.setStatus(1);
        userDto.setPassword(MD5.crypt(userDto.getPassword()));

        Results<SysUser> results = userservice.save(userDto, roleId);
        return results;
    }
    
    
    String patttern = "yyyy-MM-dd";
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(patttern),true));
        
    }
}
