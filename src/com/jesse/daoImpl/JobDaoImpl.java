package com.jesse.daoImpl;

import com.jesse.bean.Job;
import com.jesse.dao.JobDao;
import com.jesse.util.PageModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/8/8 0008.
 */
@Repository
public class JobDaoImpl implements JobDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Job selectById(String id) {
        return (Job) getCurrentSession().get(Job.class,id);
    }

    @Override
    public List<Job> selectAllJob() {
        Criteria criteria =getCurrentSession().createCriteria (Job.class);
        return criteria.list();
    }

    @Override
    public List<Job> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Job.class);
        if(params.containsKey("job")){
            Job job= (Job) params.get("job");
            if(job !=null){
                if(job.getName()!=null && !job.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+job.getName()+"%"));
                }
            }
        }
        if(params.containsKey("pageModel")){
            PageModel pageModel= (PageModel) params.get("pageModel");
            criteria.setFirstResult(pageModel.getPageFrom());
            criteria.setMaxResults(pageModel.getPageSize());
        }
        return criteria.list();
    }

    @Override
    public Integer count(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Job.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("job")){
            Job job= (Job) params.get("job");
            if(job !=null){
                if(job.getName()!=null && !job.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+job.getName()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public void deleteById(String id) {
        Job job=new Job();
        job.setId(id);
        getCurrentSession().delete(job);
    }

    @Override
    public void save(Job job) {
        getCurrentSession().save(job);
    }

    @Override
    public void update(Job job) {
        getCurrentSession().update(job);
    }
}
