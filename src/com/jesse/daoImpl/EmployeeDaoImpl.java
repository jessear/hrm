package com.jesse.daoImpl;

import com.jesse.bean.Employee;
import com.jesse.dao.EmployeeDao;
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
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Integer count(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Employee.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("employee")){
            Employee employee= (Employee) params.get("employee");
            if(employee !=null){
                if(employee.getDept()!=null){
                    if(employee.getDept().getId()!=null && !employee.getDept().getId().equals("")){
                        criteria.add(Restrictions.like("dept_id","%"+employee.getDept().getId()+"%"));
                    }
                }
                if(employee.getJob()!=null){
                    if(employee.getJob().getId()!=null && !employee.getJob().getId().equals("")){
                        criteria.add(Restrictions.like("job_id","%"+employee.getJob().getId()+"%"));
                    }
                }
                if(employee.getName()!=null && !employee.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+employee.getName()+"%"));
                }

                if(employee.getPhone()!=null && !employee.getPhone().equals("")){
                    criteria.add(Restrictions.like("phone","%"+employee.getPhone()+"%"));
                }

                if(employee.getCard_id()!=null && !employee.getCard_id().equals("")){
                    criteria.add(Restrictions.like("card_id","%"+employee.getCard_id()+"%"));
                }

                if(employee.getSex()!=null && !employee.getSex().equals("") && !employee.getSex().equals("0")){
                    criteria.add(Restrictions.like("sex","%"+employee.getSex()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public List<Employee> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Employee.class);
        if(params.containsKey("employee")){
            Employee employee= (Employee) params.get("employee");
            if(employee !=null){
                if(employee.getDept()!=null){
                    if(employee.getDept().getId()!=null && !employee.getDept().getId().equals("")){
                        criteria.add(Restrictions.like("dept_id","%"+employee.getDept().getId()+"%"));
                    }
                }
                if(employee.getJob()!=null){
                    if(employee.getJob().getId()!=null && !employee.getJob().getId().equals("")){
                        criteria.add(Restrictions.like("job_id","%"+employee.getJob().getId()+"%"));
                    }
                }
                if(employee.getName()!=null && !employee.getName().equals("")){
                    criteria.add(Restrictions.like("name","%"+employee.getName()+"%"));
                }

                if(employee.getPhone()!=null && !employee.getPhone().equals("")){
                    criteria.add(Restrictions.like("phone","%"+employee.getPhone()+"%"));
                }

                if(employee.getCard_id()!=null && !employee.getCard_id().equals("")){
                    criteria.add(Restrictions.like("card_id","%"+employee.getCard_id()+"%"));
                }

                if(employee.getSex()!=null && !employee.getSex().equals("") && !employee.getSex().equals("0")){
                    criteria.add(Restrictions.like("sex","%"+employee.getSex()+"%"));
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
    public void save(Employee employee) {
        getCurrentSession().save(employee);
    }

    @Override
    public void deleteById(String id) {
        Employee employee=new Employee();
        employee.setId(id);
        getCurrentSession().delete(employee);
    }

    @Override
    public Employee selectById(String id) {
        return (Employee) getCurrentSession().get(Employee.class,id);
    }

    @Override
    public void update(Employee employee) {
        getCurrentSession().update(employee);
    }
}
