package com.zfx.supper.service;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysRole;

import java.util.List;

public interface RoleService {


	Results<SysRole> getAllRoleByPage(Integer offset, Integer limit);
	
	Results<SysRole> findRoleByFuzzyRoleName(Integer offset, Integer limit,String rolename);

	Results<SysRole> getAllRole();
}
