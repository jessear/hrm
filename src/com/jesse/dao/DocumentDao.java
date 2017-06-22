package com.jesse.dao;

import com.jesse.bean.Document;
import com.jesse.provider.DocumentDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.jesse.common.HrmConstants.DOCUMENTTABLE;

/**
 * Created by public1 on 2017/6/21.
 */
public interface DocumentDao {

    //动态查询
    @SelectProvider(type = DocumentDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "create_date",property = "create_date",javaType = java.util.Date.class),
            @Result(column = "user_id",property = "user",
            one=@One(select = "com.jesse.dao.UserDao.selectById",
            fetchType = FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);
    @SelectProvider(type = DocumentDynaSqlProvider.class,method = "count")
    Integer count(Map<String, Object> params);
    //动态插入文档
    @InsertProvider(type = DocumentDynaSqlProvider.class,method = "insertDocument")
    void save(Document document);
    @Select("select * from "+DOCUMENTTABLE+" where id=#{id}")
    Document selectById(Integer id);
    //根据id删除公告
    @Delete("delete from "+DOCUMENTTABLE+" where id=#{id}")
    void deleteById(Integer id);
    //动态修改公告
    @UpdateProvider(type = DocumentDynaSqlProvider.class,method = "updateDocument")
    void update(Document document);
}
