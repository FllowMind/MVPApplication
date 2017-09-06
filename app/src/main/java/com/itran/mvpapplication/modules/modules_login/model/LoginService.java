package com.itran.mvpapplication.modules.modules_login.model;

import com.itran.mvpapplication.beans.Result;
import com.itran.mvpapplication.beans.User;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public interface LoginService {

    @POST("login")
    Observable<Result<User>> login(@Query("userName") String userName, @Query("password") String password);

}
