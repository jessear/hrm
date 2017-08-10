package com.jesse.dao;

import com.jesse.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface UserDao {
    //根据登录名和密码查询员工
    User selectByLoginnameAndPassword(String loginname, String password);
    //根据id查询用户
    User selectById(String id);
    //根据id删除用户
    void deleteById(String id);
    //动态修改用户
    void update(User user);
    //动态查询
    List<User> selectByPage(Map<String, Object> params);
    //根据参数查询用户总数
    Integer count(Map<String, Object> params);
    //动态插入用户
    void save(User user);
}