package com.jesse.dao;


import com.jesse.bean.Job;

import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface JobDao {
    /**
     * 根据id查询职位
     * @param id
     * @return
     */
    Job selectById(String id);

    /**
     * 查询所有职位
     * @return
     */
    List<Job> selectAllJob();

    /**
     * 动态查询
     * @param params
     * @return
     */
    List<Job> selectByPage(Map<String, Object> params);

    /**
     * 查询数目
     * @param params
     * @return
     */
    Integer count(Map<String, Object> params);

    /**
     * 删除职位
     * @param id
     */
    void deleteById(String id);

    /**
     * 插入职位
     * @param job
     */
    void save(Job job);

    /**
     * 修改职位
     * @param job
     */
    void update(Job job);
}
