package com.jesse.dao;


import com.jesse.bean.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface EmployeeDao {

    /**
     * 查询数目员工
     * @param params
     * @return
     */
    Integer count(Map<String, Object> params);
    /**
     * 动态查询员工
     * @param params
     * @return
     */
    List<Employee> selectByPage(Map<String, Object> params);

    /**
     * 动态插入员工
     * @param employee
     */
    void save(Employee employee);

    /**
     * 删除员工
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee selectById(String id);

    /**
     * 修改员工
     * @param employee
     */
    void update(Employee employee);
}
