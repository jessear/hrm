package com.jesse.impl;

import com.jesse.bean.User;
import com.jesse.dao.UserDao;
import com.jesse.service.UserService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/22.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * 自动注入持久层dao对象
     */
    @Autowired
    private UserDao userDao;
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {

        return userDao.selectByLoginnameAndPassword(loginname,password);
    }
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("user",user);
        int recordCount=userDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<User> users=userDao.selectByPage(params);
        return users;
    }

    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void modifyUserById(User user) {
        userDao.update(user);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
}
