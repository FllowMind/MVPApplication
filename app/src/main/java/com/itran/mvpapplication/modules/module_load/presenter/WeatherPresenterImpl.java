package com.itran.mvpapplication.modules.module_load.presenter;

import android.util.Log;

import com.itran.mvpapplication.beans.Result;
import com.itran.mvpapplication.beans.Weather;
import com.itran.mvpapplication.common.MyObserver;
import com.itran.mvpapplication.managers.APIServiceManager;
import com.itran.mvpapplication.modules.module_load.model.WeatherModel;
import com.itran.mvpapplication.modules.module_load.view.WeatherView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class WeatherPresenterImpl implements WeatherPresenter {
    private final String TAG = getClass().getName();

    public WeatherView weatherView;

    public WeatherPresenterImpl(WeatherView weatherView) {
        this.weatherView = weatherView;
    }

    @Override
    public void queryWeatherByCity(String city) {
        APIServiceManager.createRetrofitServer(WeatherModel.class)
                .getWeatherByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Result>() {

                    @Override
                    public void onSuccess(Result value) {
                        if (value.getStatus() == 200) {
                            Weather w = (Weather) value.getData();
                            weatherView.queryWeatherByCitySuccess(w);
                            Log.i(TAG, "success");
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG, e.toString());
                    }

                });
    }

}
