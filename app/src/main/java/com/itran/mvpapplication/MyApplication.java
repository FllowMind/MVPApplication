package com.itran.mvpapplication;

import android.app.Application;

import com.itran.mvpapplication.common.CrashCallBack;
import com.itran.mvpapplication.managers.ConfigManager;
import com.itran.mvpapplication.managers.ContextManager;
import com.itran.mvpapplication.modules.module_login.view.LoginActivity;
import com.itran.mvpapplication.utils.SoundUtil;
import com.itran.mvpapplication.utils.VibratorUtil;
import com.zxy.recovery.core.Recovery;

/**
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        ContextManager.init(this);//初始化全局上下文管理器，这个必先初始化
        ConfigManager.getIns().initConfig();//初始化全局配置
        SoundUtil.initSoundPool(this);
        VibratorUtil.initVibrator(this);
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(LoginActivity.class)
                .callback(new CrashCallBack())
                .recoverEnabled(true)
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                .init(this);
    }



}
