package com.jesse.service;


import com.jesse.bean.Employee;
import com.jesse.util.PageModel;

import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface EmployeeService {
    /**
     * 获取所有员工
     * @param employee
     * @param pageModel
     * @return
     */
    List<Employee> findEmployee(Employee employee, PageModel pageModel);

    /**
     * 根据id删除员工
     * @param id
     */
    void removeEmployeeById(String id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Employee findEmployeeById(String id);

    /**
     * 添加员工
     * @param employee
     */
    void addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    void modifyEmployee(Employee employee);
}
