package com.zfx.supper.mapper;

import com.zfx.supper.dto.RoleDto;
import com.zfx.supper.model.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    
    /*
     * 功能描述: 获取分页roles数据
     * 
     * @Param: [offset, limit]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysRole>
     * @Author: Administrator
     * @Date: 2019/11/22 0022 22:41
     */
    List<SysRole> getAllRoleByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /*
     * 功能描述: 通过角色名获取分页roles数据
     *
     * @Param: [offset, limit]
     * @Return: com.zfx.supper.base.result.Results<com.zfx.supper.model.SysRole>
     * @Author: Administrator
     * @Date: 2019/11/22 0022 22:41
     */
    List<SysRole> findRoleByFuzzyRoleName(@Param("offset") Integer offset, @Param("limit") Integer limit,@Param("rolename") String rolename);

    /*
     * 功能描述: 获取roles总数
     * 
     * @Param: []
     * @Return: int
     * @Author: Administrator
     * @Date: 2019/11/22 0022 22:41
     */
    Integer countAllRoles();
    
    /*
     * 功能描述: 通过角色名获取分页roles数据总条数
     *
     * @Param: []
     * @Return: int
     * @Author: Administrator
     * @Date: 2019/11/22 0022 22:41
     */
    Integer countAllRolesByName(String rolename);


    @Select("select * from sys_role")
    List<SysRole> getAllRole();

    int saveRole(SysRole sysRole);

    int update(RoleDto roleDto);
    
    @Select("select * from sys_role t where t.id = #{id}")
    SysRole getById(Integer id);
    
    @Delete("delete from sys_role where id = #{id}")
    void delete(Integer id);
}
