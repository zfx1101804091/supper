package com.zfx.supper.mapper;

import com.zfx.supper.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByName(String username);
}
