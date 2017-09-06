package com.itran.mvpapplication.modules.modules_load.view;

import com.itran.mvpapplication.beans.Weather;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public interface WeatherView {

    public void queryWeatherByCitySuccess(Weather weather);
    public void dismissDialog();
}
