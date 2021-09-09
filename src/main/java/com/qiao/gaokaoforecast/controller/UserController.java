package com.qiao.gaokaoforecast.controller;

import com.qiao.gaokaoforecast.domain.User;
import com.qiao.gaokaoforecast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-19 17:18
 * @since 0.1.0
 **/
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ModelAndView addUser(User user,HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        if(user==null){
            model.setViewName("error");
            return model;
        }
        String newUsername = user.getUser_name();
        String isNew = userService.isObtainUser(newUsername);
        if(isNew!=null){
            model.setViewName("UserNameError");
            return model;
        }
        int res = userService.addUser(user);
        if(res==1){
            model.setViewName("/main");
            request.getSession().setAttribute("success","success");
            return model;
        }else {
            model.setViewName("error");
            return model;
        }
    }
    @RequestMapping("/userLogOut")
    public String userLogOut(HttpServletRequest request){
        request.getSession().removeAttribute("success");
        return "/";
    }
}
