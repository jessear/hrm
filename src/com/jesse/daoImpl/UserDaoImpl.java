package com.jesse.daoImpl;

import com.jesse.bean.User;
import com.jesse.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 * Created by Jesse on 2017/8/8 0008.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public User selectByLoginnameAndPassword(String loginname, String password) {
        Query query = getCurrentSession().createQuery ("from User as u where u.loginname=:loginname and u.password=:password");
        query.setString("loginname",loginname);
        query.setString("password",password);
        return (User) query.uniqueResult();
    }

    @Override
    public User selectById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> selectByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return null;
    }

    @Override
    public void save(User user) {

    }
}
