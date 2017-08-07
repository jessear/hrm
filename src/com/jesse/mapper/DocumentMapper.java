package com.jesse.mapper;

import com.jesse.bean.Document;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface DocumentMapper {

    /**
     * 分页查询文档
     * @param params
     * @return
     */
    List<Document> selectByPage(Map<String, Object> params);

    /**
     * 查询文档数目
     * @param params
     * @return
     */
    Integer count(Map<String, Object> params);

    /**
     * 保存文档
     * @param document
     */
    void save(Document document);

    /**
     * 根据id查询文档
     * @param id
     * @return
     */
    Document selectById(String id);

    /**
     * 删除文档
     * @param id
     */
    void deleteById(String id);

    /**
     * 修改文档
     * @param document
     */
    void update(Document document);
}
