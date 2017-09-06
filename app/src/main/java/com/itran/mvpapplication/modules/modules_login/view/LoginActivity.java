package com.itran.mvpapplication.modules.modules_login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.beans.User;
import com.itran.mvpapplication.common.BaseActivity;
import com.itran.mvpapplication.modules.modules_login.presenter.LoginPresenter;
import com.itran.mvpapplication.modules.modules_login.presenter.LoginPresenterImpl;
import com.itran.mvpapplication.utils.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 淋雨又调皮 on 2017/9/4.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.settingBtn)
    ImageView settingBtn;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        loginPresenter = new LoginPresenterImpl(this);
    }


    @Override
    public void loginSuccess(User user) {

    }

    @Override
    public void loginFailed(String message) {
        showFailedDialog(message);
    }

    @OnClick({R.id.btn_login, R.id.btn_clear, R.id.settingBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_clear:
                clearInput();
                break;
            case R.id.settingBtn:
                LoginSettingsActivity.lanuch(this);
                break;
        }
    }

    /**
     * 清空输入框
     */
    private void clearInput() {
        etLoginPassword.setText("");
        etLoginUsername.setText("");
    }

    /**
     * 登录
     */
    public void login() {
        String userName = etLoginUsername.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            DialogUtil.showWarningDialog(this, R.string.password_or_username_null);
        } else {
            loginPresenter.login(userName, password);
            showCustomTextLodingDialog(R.string.logging_in);
        }
    }


}
