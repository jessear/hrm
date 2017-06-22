package com.jesse.dao;

import com.jesse.bean.Dept;
import com.jesse.provider.DeptDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import static com.jesse.common.HrmConstants.DEPTTABLE;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface DeptDao {
    //动态查询
    @SelectProvider(type=DeptDynaSqlProvider.class,method = "selectWithParam")
    List<Dept> selectByPage(Map<String,Object> params);
    @SelectProvider(type=DeptDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    @Select("select * from "+DEPTTABLE+" ")
    List<Dept> selectAllDept();
    @Select("select * from "+DEPTTABLE+" where id = #{id}")
    Dept selectById(int id);
    @Delete("delete from "+DEPTTABLE+" where id = #{id}")
    void deleteById(int id);
    //动态插入部门
    @InsertProvider(type=DeptDynaSqlProvider.class,method = "insertDept")
    void save(Dept dept);
    //动态修改部门
    @UpdateProvider(type=DeptDynaSqlProvider.class,method = "updateDept")
    void update(Dept dept);
}
