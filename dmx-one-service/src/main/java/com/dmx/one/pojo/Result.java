package com.dmx.one.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description:
 * @Date: Create at 19:39, 2017/12/18
 * @Author: Matthew
 */
public class Result<T> implements Serializable{
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
