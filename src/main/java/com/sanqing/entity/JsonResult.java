package com.sanqing.entity;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/12.
 */
public class JsonResult<T> implements Serializable{

    private static final long serialVersionUID = -2485904816682108369L;
    /**
     * 数据
     */
    private T data;
    /**
     * 信息
     */
    private String resultMessage;
    /**
     * 是否成功
     */
    private String resultCode;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public JsonResult(T data, String resultMessage, String resultCode) {
        this.data = data;
        this.resultMessage = resultMessage;
        this.resultCode = resultCode;
    }

    public JsonResult(T data, String resultMessage) {
        this.data = data;
        this.resultMessage = resultMessage;
    }

    public JsonResult(T data) {
        this.data = data;
    }

    public JsonResult() {
    }
}
