package com.qiao.gaokaoforecast.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-20 10:08
 * @since 0.1.0
 **/
//@EnableWebSecurity
public class RouterConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated();

        // 定制登录页
        http.formLogin().loginPage("/").loginProcessingUrl("/main")
                .failureUrl("/")
                .permitAll();

        // 注销，开启注销的功能
        http.logout().permitAll();
        // 关闭csrf安全功能，否则在登出的时候会失效


        // 添加开启 Remember me 功能，cookie 默认保存两周,修改为一小时
        http.rememberMe().tokenValiditySeconds(3600);
    }
}
