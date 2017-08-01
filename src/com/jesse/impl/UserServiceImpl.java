package com.jesse.impl;

import com.jesse.bean.User;
import com.jesse.mapper.UserMapper;
import com.jesse.service.UserService;
import com.jesse.util.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/22.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        return userMapper.selectByLoginnameAndPassword(loginname,password);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(String id) {
        return userMapper.selectById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("user",user);
        int recordCount=userMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<User> users=userMapper.selectByPage(params);
        return users;
    }

    @Override
    public void removeUserById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public void modifyUserById(User user) {
        userMapper.update(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.save(user);
    }
}
