package com.jesse.bean;

import java.io.Serializable;

/**
 * Created by Jesse on 2017/8/16 0016.
 */
public class Result<T> implements Serializable{

    private int code;

    private String msg;

    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
