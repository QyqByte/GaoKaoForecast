package com.qiao.gaokaoforecast.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-20 13:25
 * @since 0.1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object success = request.getSession().getAttribute("success");
        if(success==null){
            request.setAttribute("msg","没有权限，清先登录！");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
