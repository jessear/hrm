package com.jesse.impl;

import com.jesse.bean.Job;
import com.jesse.mapper.JobMapper;
import com.jesse.service.JobService;
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
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Resource
    JobMapper jobMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("job",job);
        int recordCount=jobMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Job> jobs=jobMapper.selectByPage(params);
        return jobs;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobMapper.selectAllJob();
    }

    @Override
    public void removeJobById(Integer id) {
        jobMapper.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Job findJobById(String id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void addJob(Job job) {
        jobMapper.save(job);
    }

    @Override
    public void modifyJob(Job job) {
        jobMapper.update(job);
    }

}
