package com.itran.mvpapplication.modules.modules_login.view;

import com.itran.mvpapplication.beans.User;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public interface LoginView {

    public void loginSuccess(User user);

    public void loginFailed(String message);

}
