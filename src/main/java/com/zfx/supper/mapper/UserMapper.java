package com.zfx.supper.mapper;

import com.zfx.supper.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    
    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByName(String username);
    
    /**
     * 功能描述: 查询分页数据
     *  select * from sys_user limit 5, 10 ; 检索记录行 6-15
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

    
    int updateUser(SysUser sysUser);
    
    @Delete("delete from sys_user where id = #{intValue}")
    int deleteUser(int intValue);

    /*
     * 功能描述: 模糊查询数据总数
     * 
     * @Param: [username]
     * @Return: java.lang.Long
     * @Author: Administrator
     * @Date: 2019/11/20 0020 23:11
     */
    @Select("select count(*) from sys_user t where t.username like '%${username}%'")
    Long findUserByFuzzyUserName(@Param("username") String username);

    /*
     * 功能描述: 模糊查询
     * 
     * @Param: [offset, limit, username]
     * @Return: java.util.List<com.zfx.supper.model.SysUser>
     * @Author: Administrator
     * @Date: 2019/11/20 0020 23:12
     */
    @Select("select * from sys_user where username like '%${username}%'  limit #{startPosition}, #{limit}")
    List<SysUser> findUserByFuzzyUserNameByPage(@Param("startPosition") Integer offset, @Param("limit")Integer limit, @Param("username")String username);

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);

    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id") Long id, @Param("password") String password);
}
