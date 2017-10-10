package com.itran.mvpapplication.common.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.utils.RUtil;

/**
 * 自定义的圆形进度弹出框
 * 
 * @author 淋雨又调皮
 *
 */
public class CustomProgressDialog extends AlertDialog {

	private View view;
	private TextView contentTv ;
	private String content;


	public CustomProgressDialog(Context context) {
		super(context,R.style.loading_dialog);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater= LayoutInflater.from(context);
		view = inflater.inflate(R.layout.my_progress_dialog_layout, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(view);
		this.getWindow().setDimAmount(0.5f);
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(false);
		setOnShowListener(new OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				contentTv = view.findViewById(R.id.contentTv);
				if(contentTv!=null){
					if(TextUtils.isEmpty(content)){
						contentTv.setVisibility(View.GONE);
					}else {
						contentTv.setVisibility(View.VISIBLE);
						contentTv.setText(content);
					}
				}
			}
		});
	}

	public void setContent(String content){
		this.content = content;
	}

	public void setContent(int content){
		this.content = RUtil.getString(content);
	}

}
