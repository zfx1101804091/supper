package com.zfx.supper.mapper;

import com.zfx.supper.dto.UserDto;
import com.zfx.supper.model.SysUser;
import org.apache.ibatis.annotations.*;

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

    /*
     * 功能描述: 新增用户 useGeneratedKeys 主键id自增长
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    int save(SysUser user);

    
    @Select("select * from sys_user t where t.telephone=#{telephone}")
    SysUser getUserByPhone(String telephone);

    @Select("select * from sys_user t where t.id=#{id}")
    SysUser getUserById(Long id);

    
    int updateUser(UserDto userDto);
}
