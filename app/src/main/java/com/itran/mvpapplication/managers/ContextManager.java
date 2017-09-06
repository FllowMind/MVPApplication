package com.itran.mvpapplication.managers;

import android.content.Context;

/**
 * 全局上下文管理器，主要用于需要用到上下文的地方
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class ContextManager {
    private static Context context;

    /**
     * 初始化上下文，需要在Application  中初始化才可以获得全局上下文
     *
     * @param con
     */
    public static void init(Context con) {
        context = con;
    }

    /**
     * 获取全局的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
