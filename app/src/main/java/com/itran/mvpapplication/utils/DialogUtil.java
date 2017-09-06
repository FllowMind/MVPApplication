package com.itran.mvpapplication.utils;

import android.content.Context;

import com.itran.mvpapplication.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public class DialogUtil {

    /**
     * 弹出警示框
     *
     * @param context
     * @param message
     */
    public static void showWarningDialog(Context context, String message) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(RUtil.getString(R.string.tips));
        dialog.setContentText(message);
        dialog.setConfirmText(RUtil.getString(R.string.confirm));
        dialog.show();
    }

    /**
     * 弹出警示框
     *
     * @param context
     * @param msgId
     */
    public static void showWarningDialog(Context context, int msgId) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(RUtil.getString(R.string.tips));
        dialog.setContentText(RUtil.getString(msgId));
        dialog.setConfirmText(RUtil.getString(R.string.confirm));
        dialog.show();
    }

    /**
     * 弹出失败提示框
     *
     * @param context
     * @param msgId
     */
    public static void showFaildDialog(Context context, int msgId) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText(RUtil.getString(R.string.tips));
        dialog.setContentText(RUtil.getString(msgId));
        dialog.setConfirmText(RUtil.getString(R.string.confirm));
        dialog.show();
    }

    /**
     * 弹出失败提示框
     *
     * @param context
     */
    public static void showFaildDialog(Context context, String message) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText(RUtil.getString(R.string.tips));
        dialog.setContentText(message);
        dialog.setConfirmText(RUtil.getString(R.string.confirm));
        dialog.show();
    }
}
