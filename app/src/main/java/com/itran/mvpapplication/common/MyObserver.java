package com.itran.mvpapplication.common;

import android.util.Log;

import com.itran.mvpapplication.managers.ActivityManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public abstract class MyObserver<T> implements Observer<T> {

    private final String TAG = "NetWork";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
        Log.i(TAG, "success");
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
        dismissDialog();//关闭加载框
        Log.e(TAG, e.getMessage());
    }

    @Override
    public void onComplete() {
        dismissDialog();//关闭加载框
    }

    public abstract void onSuccess(T value);

    public abstract void onFailure(Throwable e);

    public void dismissDialog() {
        BaseActivity activity = (BaseActivity) ActivityManager.getIns().getTopActivity();
        if (activity != null) {
            activity.dialogDismiss();
        }
    }

}
