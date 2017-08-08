package com.jesse.daoImpl;

import com.jesse.bean.Job;
import com.jesse.dao.JobDao;
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
public class JobDaoImpl implements JobDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public Job selectById(String id) {
        return null;
    }

    @Override
    public List<Job> selectAllJob() {
        return null;
    }

    @Override
    public List<Job> selectByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void save(Job job) {

    }

    @Override
    public void update(Job job) {

    }
}
