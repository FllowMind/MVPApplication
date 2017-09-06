package com.itran.mvpapplication.modules.module_load.view;

import com.itran.mvpapplication.beans.Weather;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public interface WeatherView {

     void queryWeatherByCitySuccess(Weather weather);
     void dismissDialog();
}
