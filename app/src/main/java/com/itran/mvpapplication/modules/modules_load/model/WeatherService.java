package com.itran.mvpapplication.modules.modules_load.model;

import com.itran.mvpapplication.beans.Result;
import com.itran.mvpapplication.beans.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public interface WeatherService {

    @GET("json.shtml")
    Observable<Result<Weather>> getWeatherByCity(@Query("city") String city);
}
