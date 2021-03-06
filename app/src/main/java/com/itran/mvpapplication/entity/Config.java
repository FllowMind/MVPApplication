package com.itran.mvpapplication.entity;

/**
 * 配置枚举类
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public enum Config {

    /**
     * 服务器地址
     */
    SERVER_ADDRESS("server_address"),
    /**
     * 语言选项
     */
    LANGUAGE("language"),
    /**
     * 是否记住账号信息
     */
    REMEMBER_ME("remember_me"),
    /**
     * 消息提醒模式
     */
    TIPS_MODE("APP_TIPS_MODE"),
    /**
     * 登录用户名
     */
    USER_NAME("userName"),
    /**
     * 登录用户名
     */
    PASSWORD("password");

    private String value;

    private Config(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
