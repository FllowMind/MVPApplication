package com.itran.mvpapplication.modules.modules_load.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.beans.Weather;
import com.itran.mvpapplication.common.BaseActivity;
import com.itran.mvpapplication.modules.modules_load.presenter.WeatherPresenter;
import com.itran.mvpapplication.modules.modules_load.presenter.WeatherPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptDialog;

public class WeatherActivity extends BaseActivity implements WeatherView {

    @BindView(R.id.weatherTv)
    TextView weatherTv;
    @BindView(R.id.cityEt)
    EditText cityEt;
    @BindView(R.id.queryBtn)
    Button queryBtn;
    private PromptDialog dialog;
    private WeatherPresenter wPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        int i = 1 / 0;
    }

    private void init() {
        dialog = new PromptDialog(this);
        wPresenter = new WeatherPresenterImpl(this);
    }

    @Override
    public void queryWeatherByCitySuccess(Weather weather) {
        weatherTv.setText(weather.toString());
    }

    @Override
    public void dismissDialog() {
        dialog.dismiss();
    }

    @OnClick(R.id.queryBtn)
    public void onViewClicked() {
        weatherTv.setText("");
        wPresenter.queryWeatherByCity(cityEt.getText().toString());
        showLodingDialog();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        showLogoutDialog();
        showUpdateDialog();
    }
}
