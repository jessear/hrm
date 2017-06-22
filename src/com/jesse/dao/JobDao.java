package com.jesse.dao;


import com.jesse.bean.Job;
import com.jesse.provider.JobDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.jesse.common.HrmConstants.JOBTABLE;

/**
 * Created by public1 on 2017/6/21.
 */
public interface JobDao {

    @Select("select * from "+JOBTABLE+" where id = #{id}")
    Job selectById(int id);
    @Select("select * from "+JOBTABLE+" ")
    List<Job> selectAllJob();
    //动态查询
    @SelectProvider(type=JobDynaSqlProvider.class,method = "selectWithParam")
    List<Job> selectByPage(Map<String, Object> params);
    @SelectProvider(type=JobDynaSqlProvider.class,method = "count")
    Integer count(Map<String, Object> params);
    @Delete("delete from "+JOBTABLE+" where id = #{id}")
    void deleteById(int id);
    //动态插入部门
    @InsertProvider(type=JobDynaSqlProvider.class,method = "insertJob")
    void save(Job job);
    //动态修改部门
    @UpdateProvider(type=JobDynaSqlProvider.class,method = "updateJob")
    void update(Job job);
}
