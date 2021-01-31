package com.kingja.sample.security;

import org.assertj.core.util.Lists;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Description:TODO
 * Create Time:2020/12/21 0021 0:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        System.out.println("name: " + name + " inputPassword :" + inputPassword);

        User user = new User();
        user.setUsername(name);
        user.setPassword(inputPassword);
        user.setAuthorityList(Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));//一定要加前缀ROLE_
        if (true) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        //支持所有的认证
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }
}
