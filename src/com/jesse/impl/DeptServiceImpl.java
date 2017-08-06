package com.jesse.impl;

import com.jesse.bean.Dept;
import com.jesse.mapper.DeptMapper;
import com.jesse.service.DeptService;
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
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Resource
    DeptMapper deptMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("dept",dept);
        int recordCount=deptMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Dept> depts=deptMapper.selectByPage(params);
        return depts;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Dept> findAllDept() {
        return deptMapper.selectAllDept();
    }

    @Override
    public void removeDeptById(String id) {
        deptMapper.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(String id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.save(dept);
    }

    @Override
    public void modifyDept(Dept dept) {
        deptMapper.update(dept);
    }
}
