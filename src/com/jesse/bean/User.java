package com.jesse.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by public1 on 2017/6/21.
 */
public class User implements Serializable {
    private String id;//用户id
    private String username;//用户名
    private String loginname;//登录名
    private String password;//密码
    private Integer status;//状态
    private Date create_date;//建档日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
