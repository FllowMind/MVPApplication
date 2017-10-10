package com.itran.mvpapplication.managers;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class ActivityManager {

    private static ActivityManager mManager;
    private Stack<Activity> mStack;

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
    public void pushToTask(Activity activity) {
        mStack.push(activity);
    }

    /**
     * 全部出栈
     */
    public void popAll() {
        while (!mStack.empty()) {
            Activity activity = mStack.pop();
            activity.finish();
        }
    }

    /**
     * 获取栈顶的Activity
     *
     * @return
     */
    public Activity getTopActivity() {
        if (!mStack.empty()) {
            return mStack.peek();
        }
        return null;
    }
}
