package com.zfx.supper.controller;


import com.zfx.supper.base.result.Results;
import com.zfx.supper.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roleuser")
@Slf4j
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/getRoleUserByUserId")
   
    public Results getRoleUserByUserId(Integer userId) {
        log.debug("getRoleUserByUserId（"+userId+"）");
        return roleUserService.getSysRoleUserByUserId(userId);
    }
}
