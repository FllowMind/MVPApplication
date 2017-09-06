package com.itran.mvpapplication.managers;

import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class ActivityManager {

    private static ActivityManager mManager;
    private Stack<AppCompatActivity> mStack;

    public ActivityManager() {
        mStack = new Stack<>();
    }

    /**
     * 获取Activity 实例
     *
     * @return
     */
    public static ActivityManager getIns() {
        if (mManager == null) {
            mManager = new ActivityManager();
        }
        return mManager;
    }

    /**
     * 将栈顶Activity出栈
     */
    public void pop() {
        if (!mStack.empty()) {
            mStack.pop();
        }
    }

    /**
     * 将一个Activity压入栈
     *
     * @param activity
     */
    public void pushToTask(AppCompatActivity activity) {
        mStack.push(activity);
    }

    /**
     * 全部出栈
     */
    public void popAll() {
        while (!mStack.empty()) {
            AppCompatActivity activity = mStack.pop();
            activity.finish();
        }
    }

    /**
     * 获取栈顶的Activity
     *
     * @return
     */
    public AppCompatActivity getTopActivity() {
        if (!mStack.empty()) {
            return mStack.peek();
        }
        return null;
    }
}
