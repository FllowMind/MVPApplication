package com.itran.mvpapplication.modules.module_login.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.beans.User;
import com.itran.mvpapplication.common.BaseActivity;
import com.itran.mvpapplication.managers.UpdateManager;
import com.itran.mvpapplication.modules.module_login.presenter.LoginPresenter;
import com.itran.mvpapplication.modules.module_login.presenter.LoginPresenterImpl;
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
    private UpdateManager updateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        loginPresenter = new LoginPresenterImpl(this);
        updateManager = new UpdateManager(this);
        checkUpdate();
    }

    private void checkUpdate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                updateManager.checkUpdate();
            }
        } else {
            updateManager.checkUpdate();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    DialogUtil.showWarningDialog(this, R.string.refuse_permission);
                } else {
                    updateManager.checkUpdate();
                }
                break;

        }
    }
}
