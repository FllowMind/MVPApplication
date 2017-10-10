package com.itran.mvpapplication.common;

import android.util.Log;

import com.itran.mvpapplication.utils.LogUtil;
import com.zxy.recovery.callback.RecoveryCallback;

/**
 * 程序崩溃回调
 * Created by 淋雨又调皮 on 2017/9/1.
 */

public class CrashCallBack implements RecoveryCallback {
    @Override
    public void stackTrace(String stackTrace) {
        LogUtil.i("crash", "stackTrace:" + stackTrace);

    }

    @Override
    public void cause(String cause) {
        Log.i("crash", "cause:" + cause);
    }

    @Override
    public void exception(String throwExceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
        LogUtil.e("crash", String.format("throwExceptionType: %s/%n throwClassName: %s%n throwMethodName: " +
                "%s%n throwLineNumber: %s", throwExceptionType, throwClassName, throwMethodName, throwLineNumber));
    }

    @Override
    public void throwable(Throwable throwable) {
        LogUtil.e("crash", "throwable:" + throwable.getMessage());
    }
}
