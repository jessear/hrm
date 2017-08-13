package com.jesse.daoImpl;

import com.jesse.bean.Dept;
import com.jesse.bean.User;
import com.jesse.dao.DeptDao;
import com.jesse.util.PageModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
public class DeptDaoImpl implements DeptDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<Dept> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Dept.class);
        if(params.containsKey("dept")){
            Dept dept= (Dept) params.get("dept");
            if(dept !=null){
                if(dept.getName()!=null && !dept.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+dept.getName()+"%"));
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
        Criteria criteria =getCurrentSession().createCriteria (Dept.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("dept")){
            Dept dept= (Dept) params.get("dept");
            if(dept !=null){
                if(dept.getName()!=null && !dept.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+dept.getName()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public List<Dept> selectAllDept() {
        Criteria criteria =getCurrentSession().createCriteria (User.class);
        return criteria.list();
    }

    @Override
    public Dept selectById(String id) {
        return (Dept) getCurrentSession().get(Dept.class,id);
    }

    @Override
    public void deleteById(String id) {
        Dept dept=new Dept();
        dept.setId(id);
        getCurrentSession().delete(dept);
    }

    @Override
    public void save(Dept dept) {
        getCurrentSession().save(dept);
    }

    @Override
    public void update(Dept dept) {
        getCurrentSession().update(dept);
    }
}
