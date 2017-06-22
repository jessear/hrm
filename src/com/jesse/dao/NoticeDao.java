package com.jesse.dao;

import com.jesse.bean.Notice;
import com.jesse.provider.NoticeDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import java.util.List;
import java.util.Map;
import static com.jesse.common.HrmConstants.NOTICETABLE;

/**
 * Created by public1 on 2017/6/21.
 */
public interface NoticeDao {

    //动态查询
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "create_date",property = "create_date"),
            @Result(column = "user_id",property = "user",
            one=@One(select = "com.jesse.dao.UserDao.selectById",
            fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);
    @SelectProvider(type = NoticeDynaSqlProvider.class,method = "count")
    Integer count(Map<String, Object> params);
    @Select("select * from "+NOTICETABLE+" where id=#{id}")
    Notice selectById(Integer id);
    //根据id删除公告
    @Delete("delete from "+NOTICETABLE+" where id=#{id}")
    void deleteById(Integer id);
    //动态插入公告
    @InsertProvider(type = NoticeDynaSqlProvider.class,method = "insertNotice")
    void save(Notice notice);
    //动态修改公告
    @UpdateProvider(type = NoticeDynaSqlProvider.class,method = "updateNotice")
    void update(Notice notice);
}
