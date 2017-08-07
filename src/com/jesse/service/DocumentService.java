package com.jesse.service;


import com.jesse.bean.Document;
import com.jesse.util.PageModel;

import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface DocumentService {
    /**
     * 获取所有文档分页
     * @param document
     * @param pageModel
     * @return
     */
    List<Document> findDocument(Document document, PageModel pageModel);

    /**
     * 根据id删除文档
     * @param id
     */
    void removeDocumentById(String id);

    /**
     * 根据id查询文档
     * @param id
     * @return
     */
    Document findDocumentById(String id);

    /**
     * 添加文档
     * @param document
     */
    void addDocument(Document document);

    /**
     * 修改文档
     * @param document
     */
    void modifyDocument(Document document);
}
