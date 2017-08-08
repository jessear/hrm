package com.jesse.daoImpl;

import com.jesse.bean.Dept;
import com.jesse.dao.DeptDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/8/8 0008.
 */
@Repository
public class DeptDaoImpl implements DeptDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public List<Dept> selectByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Dept> selectAllDept() {
        return null;
    }

    @Override
    public Dept selectById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void save(Dept dept) {

    }

    @Override
    public void update(Dept dept) {

    }
}
