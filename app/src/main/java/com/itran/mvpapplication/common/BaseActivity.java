package com.itran.mvpapplication.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.managers.ActivityManager;
import com.itran.mvpapplication.utils.RUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by 淋雨又调皮 on 2017/7/10.
 */

public class BaseActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private PromptDialog promptDialog;//酷炫的进度弹出框
    private boolean isDialogShow = false;//通用的进度提示弹出框是否已经弹出


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
        promptDialog = new PromptDialog(this);
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


    //****************************************************************************************

    /**
     * 打印消息级别日志
     *
     * @param info
     */
    public void logI(String info) {
        Log.i(TAG, info);
    }

    /**
     * 打印错误级别日志
     *
     * @param info
     */
    public void logE(String info) {
        Log.e(TAG, info);
    }

    //************************************ 弹出框 *******************************************

    /**
     * 自定义文字，加载中弹出框
     *
     * @param msg
     */
    public void showCustomTextLodingDialog(String msg) {
        promptDialog.showLoading(msg);
        isDialogShow = true;
    }

    /**
     * 自定义文字，加载中弹出框
     *
     * @param msgId
     */
    public void showCustomTextLodingDialog(int msgId) {
        promptDialog.showLoading(RUtil.getString(this, msgId));
        isDialogShow = true;
    }

    /**
     * 加载中弹出框
     */
    public void showLodingDialog() {
        promptDialog.showLoading(RUtil.getString(this, R.string.loading));
        isDialogShow = true;
    }

    /**
     * 处理中弹出框
     */
    public void showHandlingDialog() {
        promptDialog.showLoading(RUtil.getString(this, R.string.handling));
        isDialogShow = true;
    }

    /**
     * 成功弹出框
     */
    public void showSuccessDialog(int msgId) {
        promptDialog.showSuccess(RUtil.getString(this, msgId));
    }

    /**
     * 成功弹出框
     */
    public void showSuccessDialog(String msg) {
        promptDialog.showSuccess(msg);
    }

    /**
     * 失败弹出框
     */
    public void showFailedDialog(int msgId) {
        promptDialog.showError(RUtil.getString(this, msgId));
    }

    /**
     * 失败弹出框
     */
    public void showFailedDialog(String msg) {
        promptDialog.showError(msg);
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

//    /**
//     * 退出提示框
//     */
//    public void showUpdateDialog() {
//        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
//        dialog.setTitleText(RUtil.getString(R.string.title_app_update));
//        dialog.setContentText(RUtil.getString(R.string.content_app_update));
//        dialog.setCancelText(RUtil.getString(R.string.update_later));
//        dialog.setConfirmText(RUtil.getString(R.string.update_now));
//        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                sweetAlertDialog.dismiss();
//                showDownLoadDialog();
//            }
//        });
//        dialog.show();
//    }
//
//    public void showDownLoadDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_download, null);
//        final RingProgressBar progress = view.findViewById(R.id.progress);
//        final TextView download = view.findViewById(R.id.download);
//        download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progress.setProgress(50);
//            }
//        });
//        builder.setView(view);
//
//        final Dialog dialog = builder.create();
//        dialog.show();
//
//    }


    /**
     * 关闭提示框
     */
    public void dialogDismiss() {
        if (isDialogShow) {
            promptDialog.dismiss();
            isDialogShow = false;
        }
    }


}
