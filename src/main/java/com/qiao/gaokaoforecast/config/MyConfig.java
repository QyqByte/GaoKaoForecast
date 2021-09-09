package com.qiao.gaokaoforecast.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-30 11:10
 * @since 0.1.0
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/reg","/css/**","/fonts/**",
                        "/registerTest","/NotLogin.html","/images/**","/js/**","/plugins/**",
                        "/video/**","/UserNameError.html","/UserNotExist.html","/register");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                .allowedOrigins("*") //开放哪些ip、端口、域名的访问权限
                // .allowCredentials(true) //是否允许发送Cookie信息
                .allowedMethods("GET","POST","PUT","DELETE") //是否允许发送Cookie信息
                .allowedHeaders("*") //允许HTTP请求中的携带哪些Header信息
                .exposedHeaders("*")
                .allowedOriginPatterns("*");//暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//    }



}
