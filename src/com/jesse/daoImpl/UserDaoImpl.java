package com.jesse.daoImpl;

import com.jesse.bean.User;
import com.jesse.dao.UserDao;
import com.jesse.util.PageModel;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/8/8 0008.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public User selectByLoginnameAndPassword(String loginname, String password) {
        Query query = getCurrentSession().createQuery ("from User as u where u.loginname=:loginname and u.password=:password");
        query.setString("loginname",loginname);
        query.setString("password",password);
        return (User) query.uniqueResult();
    }

    @Override
    public User selectById(String id) {
        return (User) getCurrentSession().get(User.class,id);
    }

    @Override
    public void deleteById(String id) {
        User user=new User();
        user.setId(id);
        getCurrentSession().delete(user);
    }

    @Override
    public void update(User user) {
        getCurrentSession().update(user);
    }

    @Override
    public List<User> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (User.class);
        if(params.containsKey("user")){
            User user= (User) params.get("user");
            if(user !=null){
                if(user.getUsername()!=null && !user.getUsername().equals("")){
                    criteria.add(Restrictions.like("username","%"+user.getUsername()+"%"));
                }
                if(user.getStatus()!=null && !user.getStatus().equals("")){
                    criteria.add(Restrictions.like("status","%"+user.getStatus()+"%"));
                }
            }
        }
        criteria.addOrder(Order.desc("create_date"));
        if(params.containsKey("pageModel")){
            PageModel pageModel= (PageModel) params.get("pageModel");
            criteria.setFirstResult(pageModel.getPageFrom());
            criteria.setMaxResults(pageModel.getPageSize());
        }
        return criteria.list();
    }

    @Override
    public Integer count(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (User.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("user")){
            User user= (User) params.get("user");
            if(user !=null){
                if(user.getUsername()!=null && !user.getUsername().equals("")){
                    criteria.add(Restrictions.like("username","%"+user.getUsername()+"%"));
                }
                if(user.getStatus()!=null && !user.getStatus().equals("")){
                    criteria.add(Restrictions.like("status","%"+user.getStatus()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public void save(User user) {
        getCurrentSession().save(user);
    }
}
