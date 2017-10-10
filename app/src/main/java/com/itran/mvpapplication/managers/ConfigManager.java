package com.itran.mvpapplication.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.entity.Config;
import com.itran.mvpapplication.utils.RUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置管理器
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class ConfigManager {

    private static ConfigManager instance;
    private Map<Config, String> config;
    private Context context;

    public ConfigManager(Context context) {
        this.context = context;
        config = new HashMap<>();
    }

    public static ConfigManager getIns() {
        if (instance == null) {
            instance = new ConfigManager(ContextManager.getContext());
        }
        return instance;
    }

    /**
     * 初始化配置
     */
    public void initConfig() {

        //初始化服务器地址
        String serverAddress = getFromLocal(Config.SERVER_ADDRESS, RUtil.getString(context, R.string.default_server_address));
        addConfig(Config.SERVER_ADDRESS, serverAddress);

        //初始化语言选项
        String language = getFromLocal(Config.LANGUAGE, RUtil.getString(context, R.string.default_language));
        addConfig(Config.LANGUAGE, language);

        //初始化是否记住账号密码
        String rememberMe = getFromLocal(Config.REMEMBER_ME, "false");
        addConfig(Config.REMEMBER_ME, rememberMe);
    }

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public String getConfig(Config key) {
        String conf = config.get(key);
        if(TextUtils.isEmpty(conf)){
            conf = getFromLocal(key,"");
        }
        return conf;
    }

    /**
     * 添加配置到内存
     *
     * @param key
     * @param value
     */
    public void addConfig(Config key, String value) {
        config.put(key, value);
    }

    /**
     * 添加配置到内存和本地存储
     *
     * @param key
     * @param value
     */
    public void saveConfig(Config key, String value) {
        config.put(key, value);
        save2Local(key, value);
    }

    /**
     * 保存到本地
     *
     * @param key
     * @param value
     */
    public void save2Local(Config key, String value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains(key.getValue())) {
            editor.remove(key.getValue());//先移除后添加
        }
        editor.putString(key.getValue(), value);
        editor.commit();
    }

    /**
     * 从本地获取
     *
     * @param key
     * @param defaults
     * @return
     */
    public String getFromLocal(Config key, String defaults) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(key.getValue(), defaults);
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
