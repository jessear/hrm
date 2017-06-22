package com.jesse.impl;

import com.jesse.bean.Employee;
import com.jesse.dao.EmployeeDao;
import com.jesse.service.EmployeeService;
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
 * Created by Jesse on 2017/6/22 0022.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * 自动注入持久层dao对象
     */
    @Autowired
    EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("employee",employee);
        int recordCount=employeeDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Employee> employees=employeeDao.selectByPage(params);
        return employees;
    }

    @Override
    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }
}
