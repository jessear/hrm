package com.jesse.daoImpl;

import com.jesse.bean.Document;
import com.jesse.dao.DocumentDao;
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
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<Document> selectByPage(Map<String, Object> params) {
        Criteria criteria =getCurrentSession().createCriteria (Document.class);
        if(params.containsKey("document")){
            Document document= (Document) params.get("document");
            if(document !=null){
                if(document.getTitle()!=null && !document.getTitle().equals("")){
                    criteria.add(Restrictions.like("title","%"+document.getTitle()+"%"));
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
        Criteria criteria =getCurrentSession().createCriteria (Document.class);
        criteria.setProjection(Projections.rowCount());
        if(params.containsKey("document")){
            Document document= (Document) params.get("document");
            if(document !=null){
                if(document.getTitle()!=null && !document.getTitle().equals("")){
                    criteria.add(Restrictions.like("title","%"+document.getTitle()+"%"));
                }
            }
        }
        Long count= (Long) criteria.uniqueResult();
        return Integer.valueOf(count.toString());
    }

    @Override
    public void save(Document document) {
        getCurrentSession().save(document);
    }

    @Override
    public Document selectById(String id) {
        return (Document) getCurrentSession().get(Document.class,id);
    }

    @Override
    public void deleteById(String id) {
        Document document=new Document();
        document.setId(id);
        getCurrentSession().delete(document);
    }

    @Override
    public void update(Document document) {
        getCurrentSession().update(document);
    }
}
