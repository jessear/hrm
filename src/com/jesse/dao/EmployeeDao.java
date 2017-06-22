package com.jesse.dao;


import com.jesse.bean.Employee;
import com.jesse.provider.EmployeeDynaSqlProvider;
import com.jesse.provider.JobDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import java.util.List;
import java.util.Map;
import static com.jesse.common.HrmConstants.EMPLOYEETABLE;

/**
 * Created by public1 on 2017/6/21.
 */
public interface EmployeeDao {

    @SelectProvider(type=EmployeeDynaSqlProvider.class,method = "count")
    Integer count(Map<String, Object> params);
    //动态查询
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "birthday",property = "birthday",javaType = java.util.Date.class),
            @Result(column = "create_date",property = "create_date",javaType = java.util.Date.class),
            @Result(column = "dept_id",property = "dept",
            one = @One(select = "com.jesse.dao.DeptDao.selectById",
            fetchType = FetchType.EAGER)),
            @Result(column = "job_id",property = "job",
            one=@One(select = "com.jesse.dao.JobDao.selectById",
            fetchType = FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);
    //动态插入部门
    @InsertProvider(type=EmployeeDynaSqlProvider.class,method = "insertJob")
    void save(Employee employee);
    @Delete("delete from "+EMPLOYEETABLE+" where id = #{id}")
    void deleteById(int id);
    @Select("select * from "+EMPLOYEETABLE+" where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "birthday",property = "birthday",javaType = java.util.Date.class),
            @Result(column = "create_date",property = "create_date",javaType = java.util.Date.class),
            @Result(column = "dept_id",property = "dept",
                    one = @One(select = "com.jesse.dao.DeptDao.selectById",
                            fetchType = FetchType.EAGER)),
            @Result(column = "job_id",property = "job",
                    one=@One(select = "com.jesse.dao.JobDao.selectById",
                            fetchType = FetchType.EAGER))
    })
    Employee selectById(int id);
    //动态修改部门
    @UpdateProvider(type=JobDynaSqlProvider.class,method = "updateJob")
    void update(Employee employee);
}
