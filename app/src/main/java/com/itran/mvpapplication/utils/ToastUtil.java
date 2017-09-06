package com.itran.mvpapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	/**
	 * 长时的Toast
	 * 
	 * @param context
	 * @param msgId
	 */
	public static void showLongToast(Context context, int msgId) {
		Toast.makeText(context, msgId, Toast.LENGTH_LONG).show();
	}

	/**
	 * 短时的Toast
	 * 
	 * @param context
	 * @param msgId
	 */
	public static void showShortToast(Context context, int msgId) {
		Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 长时的Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showLongToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 短时的Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showShortToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

}
