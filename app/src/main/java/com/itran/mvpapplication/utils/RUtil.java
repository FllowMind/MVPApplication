package com.itran.mvpapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

import com.itran.mvpapplication.managers.ContextManager;

/**
 * @author Up
 *
 */
public class RUtil {
	
	
	/**
	 * @param context
	 * @param key
	 * @return R中对应传入的key的字符串
	 */
	public static String getString(Context context, String key){
		Resources res = context.getResources();
		int id = res.getIdentifier(key, "string", context.getPackageName());
		String result = getString(context, id);
		return result;
	}
	
	public static String getString(Context context, int id){
		String result;
		try{
			Resources res = context.getResources();
			result = res.getString(id);
			return result;
		}
		catch(NotFoundException nfe){
			nfe.printStackTrace();
			result = "Unknown String resource";
			return result;
		}
	}
	public static String getString(int id){
		String result;
		try{
			Resources res = ContextManager.getContext().getResources();
			result = res.getString(id);
			return result;
		}
		catch(NotFoundException nfe){
			nfe.printStackTrace();
			result = "Unknown String resource";
			return result;
		}
	}

	public static String[] getStringArray(Context context, int id){
		Resources res = context.getResources();
		String[] result = res.getStringArray(id);
		return result;
	}
	public static String[] getStringArray(int id){
		Resources res = ContextManager.getContext().getResources();
		String[] result = res.getStringArray(id);
		return result;
	}

	public static int getResourceId(Context context, String key, String type){
		Resources res = context.getResources();
		int id = res.getIdentifier(key, type, context.getPackageName());
		return id;
	}
}
