package com.jesse.daoImpl;

import com.jesse.bean.Employee;
import com.jesse.dao.EmployeeDao;
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
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Employee> selectByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Employee selectById(String id) {
        return null;
    }

    @Override
    public void update(Employee employee) {

    }
}
