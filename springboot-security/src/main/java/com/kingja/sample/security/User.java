package com.kingja.sample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/21 0021 0:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class User implements UserDetails {
    private int id;
    private String username;
    private String password;


    private List<GrantedAuthority> authorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    // 账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    // 账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    // 密码是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    // 账号是否可用
    @Override
    public boolean isEnabled() {
        return false;
    }
}
