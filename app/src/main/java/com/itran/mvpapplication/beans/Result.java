package com.itran.mvpapplication.beans;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class Result<T> {
    private int status;
    private T data;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
