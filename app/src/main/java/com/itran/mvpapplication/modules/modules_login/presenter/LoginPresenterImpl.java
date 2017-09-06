package com.itran.mvpapplication.modules.modules_login.presenter;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.beans.Result;
import com.itran.mvpapplication.beans.User;
import com.itran.mvpapplication.common.MyObserver;
import com.itran.mvpapplication.managers.APIServiceManager;
import com.itran.mvpapplication.modules.modules_login.model.LoginService;
import com.itran.mvpapplication.modules.modules_login.view.LoginView;
import com.itran.mvpapplication.utils.RUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String userName, String password) {
        APIServiceManager.createRetrofitServer(LoginService.class)
                .login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Result<User>>() {
                    @Override
                    public void onSuccess(Result<User> value) {
                        if (value.getStatus() == 200) {
                            loginView.loginSuccess(value.getData());
                        } else {
                            loginView.loginFailed(value.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        loginView.loginFailed(RUtil.getString(R.string.loginfailed));
                    }
                });

    }


}
