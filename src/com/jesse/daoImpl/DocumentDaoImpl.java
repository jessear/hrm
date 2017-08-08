package com.jesse.daoImpl;

import com.jesse.bean.Document;
import com.jesse.dao.DocumentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        return this.sessionFactory.openSession();
    }

    @Override
    public List<Document> selectByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return null;
    }

    @Override
    public void save(Document document) {

    }

    @Override
    public Document selectById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void update(Document document) {

    }
}
