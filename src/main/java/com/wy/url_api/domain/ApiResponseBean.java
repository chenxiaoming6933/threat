package com.wy.url_api.domain;

import java.io.Serializable;

/**
 * Created by M on 2020/2/12.
 * 返回结果实体
 */
public class ApiResponseBean implements Serializable {

    /**
     * 查询内容
     */
    private String value;

    /**
     * 查询类别
     */
    private String type;

    /**
     * 返回结果集
     */
    private Object data;

    /**
     * 响应码  200 success  !200 error
     */
    private int status;


    public ApiResponseBean() {
    }

    public ApiResponseBean(String value, String type, Object data, int status) {
        this.value = value;
        this.type = type;
        this.data = data;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
