package com.jesse.impl;

import com.jesse.bean.Employee;
import com.jesse.mapper.EmployeeMapper;
import com.jesse.service.EmployeeService;
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
 * Created by Jesse on 2017/6/22 0022.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("employee",employee);
        int recordCount=employeeMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Employee> employees=employeeMapper.selectByPage(params);
        return employees;
    }

    @Override
    public void removeEmployeeById(String id) {
        employeeMapper.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(String id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.save(employee);
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeMapper.update(employee);
    }
}
