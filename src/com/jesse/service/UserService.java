package com.jesse.service;

import com.jesse.bean.User;
import com.jesse.util.PageModel;

import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface UserService {
    /**
     * 用户登录
     * @param loginname
     * @param password
     * @return
     */
    User login(String loginname,String password);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 分页获取所有用户
     * @param user
     * @param pageModel
     * @return
     */
    List<User> findUser(User user,PageModel pageModel);

    /**
     * 根据id删除用户
     * @param id
     */
    void removeUserById(String id);

    /**
     * 修改用户
     * @param user
     */
    void modifyUserById(User user);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
}
