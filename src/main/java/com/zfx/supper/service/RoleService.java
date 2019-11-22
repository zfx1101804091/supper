package com.zfx.supper.service;

import com.zfx.supper.base.result.Results;
import com.zfx.supper.model.SysRole;

public interface RoleService {


	Results<SysRole> getAllRoleByPage(Integer offset, Integer limit);
	
	Results<SysRole> findRoleByFuzzyRoleName(Integer offset, Integer limit,String rolename);
}
