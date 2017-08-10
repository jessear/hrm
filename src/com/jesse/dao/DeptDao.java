package com.jesse.dao;

import com.jesse.bean.Dept;

import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface DeptDao {
    //动态查询部门
    List<Dept> selectByPage(Map<String, Object> params);
    //查询部门数目
    Integer count(Map<String, Object> params);
    //查询所有部门
    List<Dept> selectAllDept();
    //根据id查询部门
    Dept selectById(String id);
    //删除部门
    void deleteById(String id);
    //动态插入部门
    void save(Dept dept);
    //动态修改部门
    void update(Dept dept);
}