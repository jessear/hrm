package com.jesse.service;


import com.jesse.bean.Dept;
import com.jesse.util.PageModel;
import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface DeptService {
    /**
     * 获取所有部门分页
     * @param dept
     * @param pageModel
     * @return
     */
    List<Dept> findDept(Dept dept, PageModel pageModel);

    /**
     * 获取所有部门
     * @return
     */
    List<Dept> findAllDept();


    /**
     * 根据id删除部门
     * @param id
     */
    void removeDeptById(Integer id);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept findDeptById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 修改员工
     * @param dept
     */
    void modifyDept(Dept dept);
}
