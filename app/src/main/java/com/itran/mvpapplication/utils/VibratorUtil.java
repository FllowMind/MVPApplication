package com.itran.mvpapplication.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * 手机震动工具类
 * Created by 淋雨又调皮 on 2017/8/15.
 */

public class VibratorUtil {

    private static Vibrator vibrator;
    private static long[] pattern = {100, 400, 100, 400}; // 停止 开启 停止 开启

    /**
     * 初始化振动，推荐在application 中初始化
     *
     * @param context
     */
    public static void initVibrator(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 开始震动
     */
    public static void startVibrator() {
        if (vibrator != null) {
            vibrator.vibrate(pattern, -1);
        }
    }

}
