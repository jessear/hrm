package com.jesse.impl;

import com.jesse.bean.Job;
import com.jesse.dao.JobDao;
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
    JobDao jobDao;

    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("job",job);
        int recordCount=jobDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Job> jobs=jobDao.selectByPage(params);
        return jobs;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobDao.selectAllJob();
    }

    @Override
    public void removeJobById(String id) {
        jobDao.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Job findJobById(String id) {
        return jobDao.selectById(id);
    }

    @Override
    public void addJob(Job job) {
        jobDao.save(job);
    }

    @Override
    public void modifyJob(Job job) {
        jobDao.update(job);
    }

}
