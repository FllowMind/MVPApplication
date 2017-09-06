package com.itran.mvpapplication.beans;

import org.json.JSONObject;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class Weather {

    private String wendu;
    private String ganmao;
//    private String yesterday;
    private String aqi;
    private String city;

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

//    public String getYesterday() {
//        return yesterday;
//    }
//
//    public void setYesterday(String yesterday) {
//        this.yesterday = yesterday;
//    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
//                ", yesterday='" + yesterday + '\'' +
                ", aqi='" + aqi + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public void formObject(JSONObject json) {
        this.wendu = json.optString("wendu");
        this.ganmao = json.optString("ganmao");
//        this.yesterday = json.optString("yesterday");
        this.aqi = json.optString("aqi");
        this.city = json.optString("city");
    }
}
