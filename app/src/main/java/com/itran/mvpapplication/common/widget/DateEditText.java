package com.itran.mvpapplication.common.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 淋雨又调皮 on 2017/7/10.
 */

public class DateEditText extends EditText implements View.OnFocusChangeListener {


    private Calendar calendar;// 日历
    private DatePickerDialog dialog;
    private String pattern = "yyyy-MM-dd";
    private SimpleDateFormat sf;

    public DateEditText(Context context) {
        super(context);
        initFDateEt();
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFDateEt();
    }

    public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFDateEt();
    }

    /**
     * 初始化时日期输入框
     */
    private void initFDateEt() {
        // 设置当前日期
        calendar = Calendar.getInstance();
        this.setOnClickListener(new DatePickListener(getContext()));
        this.setOnFocusChangeListener(this);
        this.setInputType(InputType.TYPE_NULL);// 禁止弹出软键盘
        sf = new SimpleDateFormat(pattern);
        this.setText(sf.format(new Date()));
    }

    /**
     * 设置航班日期
     */
    private void selectDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSet, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    /**
     * @description 日期设置匿名类
     */
    DatePickerDialog.OnDateSetListener dateSet = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String str = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " 00:00:00";
            try {
                str = sf.format(sf.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (isFocused()) {
                setText(str);
            }
        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            selectDate();
        }
    }


    public class DatePickListener implements OnClickListener, DatePickerDialog.OnDateSetListener {
        private String _TAG = "DatePickListener";
        private Context context;
        private EditText et;

        public DatePickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Log.d(_TAG, "etDate Clicked");
            Calendar cal = Calendar.getInstance();
            this.et = (EditText) v;
            if (et.getText().toString().trim().equals("")) {
            } else {
                Date date;
                try {
                    date = sf.parse(et.getText().toString());
                    cal.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int date = cal.get((Calendar.DATE));
            DatePickerDialog dpd = new DatePickerDialog(context, this, year, month, date);
            dpd.show();

        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Date date = cal.getTime();
            et.setText(sf.format(date));
        }


    }


}
