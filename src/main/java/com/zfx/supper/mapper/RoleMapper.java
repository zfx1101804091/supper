package com.zfx.supper.mapper;

import com.zfx.supper.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {


    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();
}
