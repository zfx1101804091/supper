package com.zfx.supper.mapper;

import com.zfx.supper.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    
    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByName(String username);
    
    /**
     * 功能描述: 查询分页数据
     */
    @Select("select * from sys_user order by id  limit #{startPosition},#{limit}")
    List<SysUser> getAllUsersByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
    
    /*
     * 功能描述: 分页总条数
     */
    @Select("select count(*) from sys_user")
    Long countAllUsers();
}
