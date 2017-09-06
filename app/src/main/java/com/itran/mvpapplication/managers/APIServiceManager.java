package com.itran.mvpapplication.managers;

import com.itran.mvpapplication.beans.Config;
import com.itran.mvpapplication.interceptors.LoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class APIServiceManager {

    private static String server_address = "";
    private static OkHttpClient client;

    static {
        client = new OkHttpClient().newBuilder()
                .addInterceptor(new LoggingInterceptor())//添加日志拦截器
                .build();
        server_address = ConfigManager.getIns().getConfig(Config.SERVER_ADDRESS);//获取服务器地址
    }

    /**
     * 创建retrofit服务
     *
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T createRetrofitServer(Class<T> classz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server_address)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(classz);
    }
}
