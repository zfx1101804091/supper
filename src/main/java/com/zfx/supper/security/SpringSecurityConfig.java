package com.zfx.supper.security;

import com.zfx.supper.security.authentication.MyAuthenctiationFailureHandler;
import com.zfx.supper.security.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: 自定义登陆成功处理
 * @author: zheng-fx
 * @time: 2019/12/15 0015 16:43
 */

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    
    /*
        加密密码
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 功能描述: http请求的安全处理
     * 
     * @Param: [http]
     * @Return: void
     * @Author: Administrator
     * @Date: 2019/12/15 0015 16:45
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        //CSRF跨站点请求伪造(Cross—Site Request Forgery)
        http.csrf().disable();
        /*
        http.authorizeRequests().anyRequest().authenticated();//意思是任何http请求都需要验证
        下面这段意思是这些路径下的资源是允许访问的，不去拦截
         ---------------------------------------
         .antMatchers("/xadmin/**",
                        "/treetable-lay/**",
                        "/ztree/**",
                        "/static/**")
                .permitAll()
          --------------------------------------      
         */
        http.authorizeRequests()
                .antMatchers("/login.html",
                        "/xadmin/**",
                        "/treetable-lay/**",
                        "/ztree/**",
                        "/static/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        //指向自定义的登陆页面
        http.formLogin()
                .loginPage("/login.html")//页面
                .loginProcessingUrl("/login")//请求的地址
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
        ;
                
    }
}
