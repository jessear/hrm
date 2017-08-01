package com.jesse.controller;

import com.jesse.bean.User;
import com.jesse.common.HrmConstants;
import com.jesse.service.UserService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Jesse on 2017/6/22 0022.
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 用户登录
     * @param loginname
     * @param password
     * @param httpSession
     * @param modelAndView
     * @return
     */
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

    /**
     * 查询用户
     * @param pageIndex
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "user/selectUser")
    public String selectUser(Integer pageIndex,
                             @ModelAttribute User user,
                             Model model){
        PageModel pageModel=new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询用户信息
        List<User> users=userService.findUser(user,pageModel);
        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "user/user";
    }

    /**
     * 删除用户
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView modelAndView){
        String[] idArray = ids.split(",");
        for(String id : idArray){
            userService.removeUserById(id);
        }
        modelAndView.setViewName("redirect:/user/selectUser");
        return modelAndView;
    }

    /**
     * 修改用户
     * @param flag flag标记,1.表示跳转到修改页面2.表示执行修改操作
     * @param user
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "user/updateUser")
    public ModelAndView updateUser(String flag,
                                   @ModelAttribute User user,
                                   ModelAndView modelAndView){
        if(flag.equals("1")){
            //根据id查询用户
            User target=userService.findUserById(user.getId());
            //设置Model数据
            modelAndView.addObject("user",target);
            //返回修改员工界面
            modelAndView.setViewName("user/showUpdateUser");
        }else{
            userService.modifyUserById(user);
            modelAndView.setViewName("redirect:/user/selectUser");
        }
        return modelAndView;
    }
    /**
     * 添加用户
     * @param flag flag标记,1.表示跳转到添加页面2.表示执行添加操作
     * @param user
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "user/addUser")
    public ModelAndView addUser(String flag,
                                   @ModelAttribute User user,
                                   ModelAndView modelAndView){
        if(flag.equals("1")){
            //设置跳转添加用户界面
            modelAndView.setViewName("user/showAddUser");
        }else{
            userService.addUser(user);
            modelAndView.setViewName("redirect:/user/selectUser");
        }
        return modelAndView;
    }
}
