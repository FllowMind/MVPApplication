package com.itran.mvpapplication.utils;

import android.util.Log;

/**
 * 日志控制工具，控制是否打印某些级别日志
 * Created by 淋雨又调皮 on 2017/10/10.
 */

public class LogUtil {
    private static final int current_log_level = 3;//当前日志级别
    private static final int verbose_log = 1;
    private static final int debug_log = 2;
    private static final int info_log = 3;
    private static final int warn_log = 4;
    private static final int error_log = 5;

    public static void v(String tag, String message) {
        if (current_log_level <= verbose_log) {
            Log.v(tag, message);
        }
    }
    public static void v(String tag, String message,Throwable e) {
        if (current_log_level <= verbose_log) {
            Log.v(tag, message,e);
        }
    }
    public static void d(String tag, String message) {
        if (current_log_level <= debug_log) {
            Log.d(tag, message);
        }
    }
    public static void d(String tag, String message,Throwable e) {
        if (current_log_level <= debug_log) {
            Log.d(tag, message,e);
        }
    }
    public static void i(String tag, String message) {
        if (current_log_level <= info_log) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message,Throwable e) {
        if (current_log_level <= info_log) {
            Log.i(tag, message,e);
        }
    }
    public static void w(String tag, String message) {
        if (current_log_level <= warn_log) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message,Throwable e) {
        if (current_log_level <= warn_log) {
            Log.i(tag, message,e);
        }
    }
    public static void e(String tag, String message) {
        if (current_log_level <= error_log) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message,Throwable e) {
        if (current_log_level <= error_log) {
            Log.e(tag, message,e);
        }
    }

}
