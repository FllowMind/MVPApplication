package com.itran.mvpapplication.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求拦截器
 * Created by 淋雨又调皮 on 2017/8/31.
 */

public class LoggingInterceptor implements Interceptor {
    private final String TAG = "NetWork";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //打印请求信息
        long t1 = System.nanoTime();
        Log.i(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

        //继续发送请求
        Response response = chain.proceed(request);
        //打印响应信息
        long t2 = System.nanoTime();
        Log.i(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
