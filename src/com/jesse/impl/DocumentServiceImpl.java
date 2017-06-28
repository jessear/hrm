package com.jesse.impl;


import com.jesse.bean.Document;
import com.jesse.mapper.DocumentMapper;
import com.jesse.service.DocumentService;
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
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    /**
     * 自动注入持久层dao对象
     */
    @Resource
    DocumentMapper documentMapper;
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("document",document);
        int recordCount=documentMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Document> documents=documentMapper.selectByPage(params);
        return documents;
    }

    @Override
    public void removeDocumentById(Integer id) {
        documentMapper.deleteById(id);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentMapper.selectById(id);
    }

    @Override
    public void addDocument(Document document) {
        documentMapper.save(document);
    }

    @Override
    public void modifyDocument(Document document) {
        documentMapper.update(document);
    }
}
