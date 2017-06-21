package com.jesse.dao;

import com.jesse.bean.User;
import static com.jesse.common.HrmConstants.USERTABLE;
import com.jesse.provider.UserDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface UserDao {
    //根据登录名和密码查询员工
    @Select("select * from "+ USERTABLE+" where loginname=#{loginname} and password=#{password}")
    User selectByLoginnameAndPassword(@Param("loginname")String loginname,@Param("password")String password);
    //根据id查询用户
    @Select("select * from "+USERTABLE+" where id=#{id}")
    User selectById(Integer id);
    //根据id删除用户
    @Delete("delete from "+USERTABLE+" where id=#{id}")
    void deleteById(Integer id);
    //动态修改用户
    @UpdateProvider(type = UserDynaSqlProvider.class,method = "updateUser")
    void update(User user);
    //动态查询
    @SelectProvider(type = UserDynaSqlProvider.class,method = "selectWithParam")
    List<User> selectByPage(Map<String,Object> params);
    //根据参数查询用户总数
    @SelectProvider(type = UserDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    //动态插入用户
    @InsertProvider(type = UserDynaSqlProvider.class,method = "insertUser")
    void save(User user);
}
