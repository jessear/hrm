package com.jesse.daoImpl;

import com.jesse.bean.Notice;
import com.jesse.dao.NoticeDao;
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
public class NoticeDaoImpl implements NoticeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<Notice> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Notice.class);
        if(params.containsKey("notice")){
            Notice notice= (Notice) params.get("notice");
            if(notice !=null){
                if(notice.getTitle()!=null && !notice.getTitle().equals("")){
                    criteria.add(Restrictions.like("title","%"+notice.getTitle()+"%"));
                }
                if(notice.getContent()!=null && !notice.getContent().equals("")){
                    criteria.add(Restrictions.like("content","%"+notice.getContent()+"%"));
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
        Criteria criteria =getCurrentSession().createCriteria (Notice.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("notice")){
            Notice notice= (Notice) params.get("notice");
            if(notice !=null){
                if(notice.getTitle()!=null && !notice.getTitle().equals("")){
                    criteria.add(Restrictions.like("title","%"+notice.getTitle()+"%"));
                }
                if(notice.getContent()!=null && !notice.getContent().equals("")){
                    criteria.add(Restrictions.like("content","%"+notice.getContent()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public Notice selectById(String id) {
        return (Notice) getCurrentSession().get(Notice.class,id);
    }

    @Override
    public void deleteById(String id) {
        Notice notice=new Notice();
        notice.setId(id);
        getCurrentSession().delete(notice);
    }

    @Override
    public void save(Notice notice) {
        getCurrentSession().save(notice);
    }

    @Override
    public void update(Notice notice) {
        getCurrentSession().update(notice);
    }
}
