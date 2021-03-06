package com.itran.mvpapplication.managers;

import com.itran.mvpapplication.common.JsonConverterFactory;
import com.itran.mvpapplication.entity.Config;
import com.itran.mvpapplication.interceptors.NetWorkInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Retrofit 的服务管理器
 * Created by 淋雨又调皮 on 2017/8/30.
 */

public class APIServiceManager {

    private static String server_address = "";
    private static OkHttpClient client;

    static {
        client = new OkHttpClient().newBuilder()
                .addInterceptor(new NetWorkInterceptor())//添加日志拦截器
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
                .addConverterFactory(JsonConverterFactory.create())
                .build();
        return retrofit.create(classz);
    }
}
