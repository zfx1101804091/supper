package com.zfx.supper.controller;

import com.zfx.supper.base.result.PageTableRequest;
import com.zfx.supper.base.result.ResponseCode;
import com.zfx.supper.base.result.Results;
import com.zfx.supper.dto.UserDto;
import com.zfx.supper.model.SysUser;
import com.zfx.supper.service.Userservice;
import com.zfx.supper.util.MD5;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
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
    @PreAuthorize("hasAuthority('sys:user:query')")
    @ApiOperation(value = "分页获取用户信息",notes = "分页获取用户信息")//描述
    @ApiImplicitParam(name = "pageTable",value = "分页查询实体类",required = false)//一个请求参数
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
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
    public String addUser(Model model){
        //初始化SysUser，防止前端thymeleaf取值时报异常 th:value="${sysUser.sex}"
        model.addAttribute(new SysUser());
        return "user/user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "保存用户信息", notes = "保存新增的用户信息")//描述
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId){
        
        SysUser sysUser = null;
        sysUser = userservice.getUserByPhone(userDto.getTelephone());
        
        if(sysUser!=null&&!(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        
        userDto.setStatus(1);
        //userDto.setPassword(MD5.crypt(userDto.getPassword()));
        
        //修改密码的加密方式为spring security的方式
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

        Results<SysUser> results = userservice.save(userDto, roleId);
        return results;
    }
    
    
    String patttern = "yyyy-MM-dd";
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(patttern),true));
        
    }

    @GetMapping("/edit")
    //@PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation(value = "用户编辑页面", notes = "跳转到用户信息编辑页面")//描述
    @ApiImplicitParam(name = "sysUser", value = "用户实体类", dataType = "SysUser")
    public String editUser(Model model,SysUser sysUser){
        
        model.addAttribute(userservice.getUserById(sysUser.getId()));
        return "user/user-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation(value = "保存用户信息", notes = "保存编辑完的用户信息")//描述
    public Results<SysUser> updateUser(UserDto userDto,Integer roleId){

        SysUser sysUser = null;
        sysUser = userservice.getUserByPhone(userDto.getTelephone());

        if(sysUser!=null&&!(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        return userservice.updateUser(userDto,roleId);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:del')")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")//描述
    @ApiImplicitParam(name = "userDto", value = "用户实体类", required = true, dataType = "UserDto")
    public Results<SysUser> deleteUser( UserDto userDto) {
        int count = userservice.deleteUser(userDto.getId());
        if(count>0){
            return Results.success();
        }
        return Results.failure();
    }



    @GetMapping("/findUserByFuzzyUserName")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
    @ApiOperation(value = "模糊查询用户信息", notes = "模糊搜索查询用户信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "模糊搜索的用户名", required = true),
            //@ApiImplicitParam(name = "pageTable",value = "分页查询实体类", required = false)
    })
    public Results<SysUser> findUserByFuzzyUserName(PageTableRequest pageTable,String username){
        
        log.debug("UserController.findUserByFuzzyUserName(): param (pageTable = {}---username = {}) ",pageTable,username);

        pageTable.countOffset();

        return userservice.findUserByFuzzyUserName(pageTable.getOffset(),pageTable.getLimit(),username);
    }


    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码")
    @ResponseBody
    public Results<SysUser> changePassword(String username, String oldPassword, String newPassword) {
        return userservice.changePassword(username, oldPassword, newPassword);
    }
    
}
