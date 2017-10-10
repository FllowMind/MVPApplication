package com.itran.mvpapplication.common;

import com.itran.mvpapplication.managers.ActivityManager;
import com.itran.mvpapplication.utils.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public abstract class CommonObserver<T> implements Observer<T> {

    private final String TAG = "NetWork";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
        LogUtil.i(TAG,"请求成功");
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
        dismissDialog();//关闭加载框
        LogUtil.e(TAG,"请求失败",e);
    }

    @Override
    public void onComplete() {
        dismissDialog();//关闭加载框
    }

    public abstract void onSuccess(T value);

    public abstract void onFailure(Throwable e);

    public void dismissDialog() {
        if(ActivityManager.getIns().getTopActivity() instanceof BaseActivity){
            ((BaseActivity) ActivityManager.getIns().getTopActivity()).dialogDismiss();
        }else {
            ((BaseFragmentActivity) ActivityManager.getIns().getTopActivity()).dialogDismiss();
        }
    }

}
