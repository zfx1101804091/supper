package com.zfx.supper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zfx.supper.model.SysPermission;
import com.zfx.supper.model.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 处理用户校验逻辑
 * @author: zheng-fx
 * @time: 2019/12/15 0015 17:12
 */
@Data
public class LoginUser extends SysUser implements UserDetails {

    private List<SysPermission> permissions;
    
    //返回已经认证授予的权限（获取权限集合）
    //这里应用了java8的新特性stream流的写法；filter里时筛选条件，map是将p.getPermission()转换类型
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }
    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return getStatus() != Status.LOCKED;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
