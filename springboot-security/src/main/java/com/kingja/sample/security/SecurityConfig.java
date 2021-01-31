package com.kingja.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 18:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    /**
//     * 自定义认证策略
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").roles("admin").password("admin")
//                .and()
//                .withUser("tom").roles("user").password("tom");
//    }
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSucessHandler;

    /**
     * 自定义认证策略
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf防护
                .csrf().disable()
                .headers().frameOptions().disable()
                .and();
        http
                .formLogin()
                //自定义登录页
                .loginPage("/login_page")
                //登录处理接口
                .loginProcessingUrl("/login")
                //用户名的key
                .usernameParameter("username")
                //密码的key
                .passwordParameter("password")
                //登录成功处理
                .successHandler(myAuthenticationSucessHandler)
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        AuthenticationException e) throws IOException,
                            ServletException {
                        System.out.println("登录失败: " + httpServletRequest.getServletPath());
                        System.out.println("登录失败: " + e.toString());
                    }
                })
                //和表单登录相关的接口统统都直接通过
                .permitAll()
                .and()
                .authorizeRequests()//开启登录配置
                .antMatchers("/user/**").hasRole("USER") // /user 这个接口，需要具备 user 这个角色
                .antMatchers("/admin/**").hasRole("USER")
                .antMatchers("/visitor/**").permitAll()
                .antMatchers("/account/**").permitAll()
                .antMatchers("/login_page").permitAll()
                //剩余接口，登录之后就能访问
                .anyRequest().authenticated()
                .and()

                .logout()
                .logoutUrl("logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                Authentication authentication) throws IOException, ServletException {

                    }
                })
                .permitAll();

        //关闭csrf，不然登录后报403
//        http.csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //不拦截
        web.ignoring().antMatchers("/letmego");
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/css/**");
//        web.ignoring().antMatchers("/js/**");
//        web.ignoring().antMatchers("/images/**");
//        //解决服务注册url被拦截的问题
//        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }


}
