package com.jesse.bean;

import java.io.Serializable;

/**
 * Created by public1 on 2017/6/21.
 */
public class Dept implements Serializable{
    private Integer id;//id
    private String name;//部门名称
    private String remark;//详细描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
