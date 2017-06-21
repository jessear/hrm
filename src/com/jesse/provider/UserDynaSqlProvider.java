package com.jesse.provider;

import com.jesse.bean.User;
import org.apache.ibatis.jdbc.SQL;
import static com.jesse.common.HrmConstants.USERTABLE;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public class UserDynaSqlProvider {

    //分页动态查询
    public String selectWithParam(Map<String,Object> params){
        String sql=new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
                if(params.get("user")!=null){
                    User user= (User) params.get("user");
                    if(user.getUsername()!=null && !user.getUsername().equals("")){
                        WHERE(" username LIKE CONCAT ('%',#{user.username},'%')");
                    }
                    if(user.getStatus()!=null && !user.getStatus().equals("")){
                        WHERE(" status LIKE CONCAT ('%',#{user.status},'%')");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") !=null){
            sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return sql;
    }
    //动态查询总数量
    public String count(Map<String,Object> params){
        return new SQL(){
            {
              SELECT("count(*)");
              FROM(USERTABLE);
              if(params.get("user")!=null){
                  User user= (User) params.get("user");
                  if(user.getUsername()!=null && !user.getUsername().equals("")){
                      WHERE(" username LIKE CONCAT ('%',#{user.username},'%')");
                  }
                  if(user.getStatus()!=null && !user.getStatus().equals("")){
                      WHERE(" status LIKE CONCAT ('%',#{user.status},'%')");
                  }
              }
            }
        }.toString();
    }
    //动态插入
    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if(user.getUsername()!=null && !user.getUsername().equals("")){
                    VALUES("username","#{username}");
                }
                if(user.getStatus()!=null && !user.getStatus().equals("")){
                    VALUES("status","#{status}");
                }
                if(user.getLoginname()!=null && !user.getLoginname().equals("")){
                    VALUES("loginname","#{loginname}");
                }
                if(user.getPassword()!=null && !user.getPassword().equals("")){
                    VALUES("password","#{password}");
                }
            }
        }.toString();
    }

    //动态插入
    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if(user.getUsername()!=null){
                    SET(" username= #{username} ");
                }
                if(user.getStatus()!=null && !user.getStatus().equals("")){
                    SET(" status= #{status} ");
                }
                if(user.getLoginname()!=null && !user.getLoginname().equals("")){
                    SET(" loginname= #{loginname} ");
                }
                if(user.getPassword()!=null && !user.getPassword().equals("")){
                    SET(" password= #{password} ");
                }
                WHERE(" id=#{id} ");
            }
        }.toString();
    }

}
