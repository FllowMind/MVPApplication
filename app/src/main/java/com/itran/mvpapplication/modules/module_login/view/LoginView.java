package com.itran.mvpapplication.modules.module_login.view;

import com.itran.mvpapplication.beans.User;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public interface LoginView {

    void loginSuccess(User user);

    void loginFailed(String message);

}
