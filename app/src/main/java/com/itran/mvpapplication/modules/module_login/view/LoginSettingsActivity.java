package com.itran.mvpapplication.modules.module_login.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.beans.Config;
import com.itran.mvpapplication.common.BaseActivity;
import com.itran.mvpapplication.managers.ConfigManager;
import com.itran.mvpapplication.utils.RUtil;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置界面
 */
public class LoginSettingsActivity extends BaseActivity {
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.remmber_me)
    CheckBox remmberMe;
    private Spinner spServerAddresses;
    private Spinner spLanguage;
    private String currentlanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_settings);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        spServerAddresses = (Spinner) findViewById(R.id.sp_server_addresses);
        spLanguage = (Spinner) findViewById(R.id.sp_language);

        //初始化服务器地址
        String serverAddress = ConfigManager.getIns().getConfig(Config.SERVER_ADDRESS);
        String[] serveraddresses = RUtil.getStringArray(this, R.array.server_addresses);
        for (int i = 0; i < serveraddresses.length; i++) {
            String s = serveraddresses[i];
            if (s.equals(serverAddress))
                spServerAddresses.setSelection(i);
        }

        //初始化语言
        currentlanguage = ConfigManager.getIns().getConfig(Config.LANGUAGE);
        String[] appLanguages = RUtil.getStringArray(this, R.array.app_languages);
        for (int i = 0; i < appLanguages.length; i++) {
            if (appLanguages[i].equals(currentlanguage)) {
                spLanguage.setSelection(i);
            }
        }

        String rememberMeStr = ConfigManager.getIns().getConfig(Config.REMEMBER_ME);
        if (rememberMeStr.equals("true")) {
            remmberMe.setChecked(true);
        } else {
            remmberMe.setChecked(false);
        }

    }


    @OnClick({R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                save();
                break;
        }
    }

    /**
     * 保存设置
     */
    public void save() {
        String address = spServerAddresses.getSelectedItem().toString().trim();
        ConfigManager.getIns().saveConfig(Config.SERVER_ADDRESS, address);
        String language = spLanguage.getSelectedItem().toString().trim();
        ConfigManager.getIns().saveConfig(Config.LANGUAGE, language);
        if (remmberMe.isChecked()) {
            ConfigManager.getIns().saveConfig(Config.REMEMBER_ME, "true");
        } else {
            ConfigManager.getIns().saveConfig(Config.REMEMBER_ME, "false");
        }
        ConfigManager.getIns().initConfig();
        Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        if (!language.equals(currentlanguage)) {
            switchLanguage(language);
            Intent intent = new Intent(LoginSettingsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            finish();
        }
    }

    /**
     * 切换语言
     */
    private void switchLanguage(String language) {
        // TODO Auto-generated method stub
        // 应用内配置语言
        Resources resources = getResources();// 获得res资源对象
        Configuration config = resources.getConfiguration();// 获得设置对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        if (language.equals("简体中文")) {
            config.setLocale(Locale.SIMPLIFIED_CHINESE); // 简体中文
        } else if (language.equals("English")) {
            config.setLocale(Locale.ENGLISH); // 英文
        }
        resources.updateConfiguration(config, dm);
    }

    /**
     * 启动设置界面
     *
     * @param context
     */
    public static void lanuch(Context context) {
        Intent intent = new Intent(context, LoginSettingsActivity.class);
        context.startActivity(intent);
    }


}
