package com.jesse.task;

import com.jesse.bean.User;
import com.jesse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Jesse on 2017/8/15 0015.
 */
public class MyTask {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    public void execute(){
//        List<User> users=userService.findUser(null,null);
//        for(User user :users){
//            System.out.println(user.getUsername());
//        }
    }
}
