package com.jesse.service;


import com.jesse.bean.Job;
import com.jesse.util.PageModel;

import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface JobService {
    /**
     * 获取所有职位分页
     * @param job
     * @param pageModel
     * @return
     */
    List<Job> findJob(Job job, PageModel pageModel);

    /**
     * 获取所有职位
     * @return
     */
    List<Job> findAllJob();

    /**
     * 根据id删除职位
     * @param id
     */
    void removeJobById(Integer id);

    /**
     * 根据id查询职位
     * @param id
     * @return
     */
    Job findJobById(String id);

    /**
     * 添加职位
     * @param job
     */
    void addJob(Job job);

    /**
     * 修改职位
     * @param job
     */
    void modifyJob(Job job);
}
