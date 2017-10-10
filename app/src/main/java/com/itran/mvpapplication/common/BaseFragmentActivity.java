package com.itran.mvpapplication.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.common.widget.CustomProgressDialog;
import com.itran.mvpapplication.managers.ActivityManager;
import com.itran.mvpapplication.utils.RUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 淋雨又调皮 on 2017/9/29.
 */

public class BaseFragmentActivity extends FragmentActivity {

    private final String TAG = getClass().getName();
    private CustomProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getIns().pushToTask(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        progressDialog = new CustomProgressDialog(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getIns().pop();
    }

    /**
     * 注销
     */
    public void logout() {
        ActivityManager.getIns().popAll();
    }

    /**
     * 自定义文字，加载中弹出框
     *
     * @param msg
     */
    public void showCustomTextLodingDialog(String msg) {
        progressDialog.setContent(msg);
        progressDialog.show();
    }

    /**
     * 自定义文字，加载中弹出框
     *
     * @param msgId
     */
    public void showCustomTextLodingDialog(int msgId) {
        progressDialog.setContent(msgId);
        progressDialog.show();
    }

    /**
     * 加载中弹出框
     */
    public void showLodingDialog() {
        progressDialog.setContent(R.string.loading);
        progressDialog.show();
    }

    /**
     * 处理中弹出框
     */
    public void showHandlingDialog() {
        progressDialog.setContent(R.string.handling);
        progressDialog.show();
    }

    /**
     * 退出提示框
     */
    public void showLogoutDialog() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(RUtil.getString(R.string.tips));
        dialog.setContentText(RUtil.getString(R.string.logout_tips));
        dialog.setCancelText(RUtil.getString(R.string.cancel));
        dialog.setConfirmText(RUtil.getString(R.string.confirm));
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                logout();
            }
        });
        dialog.show();
    }


    /**
     * 关闭提示框
     */
    public void dialogDismiss() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
