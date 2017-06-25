package com.jesse.controller;

import com.jesse.bean.User;
import com.jesse.common.HrmConstants;
import com.jesse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Jesse on 2017/6/22 0022.
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login(
            @RequestParam("loginname") String loginname,
            @RequestParam("password") String password,
            HttpSession httpSession,ModelAndView modelAndView){
        User user=userService.login(loginname,password);
        if(user!=null){
            //登录成功将用户保存到session中
            httpSession.setAttribute(HrmConstants.USER_SESSION,user);
            modelAndView.setViewName("redirect:/main");
        }else{
            modelAndView.addObject("message","登录名或密码错误！请重试");
            modelAndView.setViewName("forward:/loginForm");
        }
        return modelAndView;
    }

}
