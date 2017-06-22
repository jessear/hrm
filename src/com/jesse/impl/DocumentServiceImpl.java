package com.jesse.impl;


import com.jesse.bean.Document;
import com.jesse.dao.DocumentDao;
import com.jesse.service.DocumentService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/6/22 0022.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    /**
     * 自动注入持久层dao对象
     */
    @Autowired
    DocumentDao documentDao;
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("document",document);
        int recordCount=documentDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Document> documents=documentDao.selectByPage(params);
        return documents;
    }

    @Override
    public void removeDocumentById(Integer id) {
        documentDao.deleteById(id);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectById(id);
    }

    @Override
    public void addDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public void modifyDocument(Document document) {
        documentDao.update(document);
    }
}
