package com.itran.mvpapplication.entity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class Result{
    private boolean success;
    private String msg;
    private JSONObject detail;
    private JSONArray list;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getDetail() {
        return detail;
    }

    public void setDetail(JSONObject detail) {
        this.detail = detail;
    }

    public JSONArray getList() {
        return list;
    }

    public void setList(JSONArray list) {
        this.list = list;
    }

    public void formJSONObject(org.json.JSONObject json) {
        this.success = json.optBoolean("success");
        this.msg = json.optString("msg");
        this.detail = json.optJSONObject("detail");
        this.list = json.optJSONArray("list");
    }

}
