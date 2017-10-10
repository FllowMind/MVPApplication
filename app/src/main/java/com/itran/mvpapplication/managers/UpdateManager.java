package com.itran.mvpapplication.managers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itran.mvpapplication.R;
import com.itran.mvpapplication.entity.Config;
import com.itran.mvpapplication.entity.Result;
import com.itran.mvpapplication.common.CommonObserver;
import com.itran.mvpapplication.utils.DialogUtil;
import com.itran.mvpapplication.utils.RUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.GET;

/**
 * 应用检查更新管理器
 * <p>
 * Created by 淋雨又调皮 on 2017/9/6.
 */

public class UpdateManager {

    private final String TAG = getClass().getName();
    private final int UPDATE_PROGRESS = 1;//更新进度
    private final int FINISH_DOWNLOAD = 2;//完成下载
    private final int DOWNLOAD_FAILURE = 3;//下载失败
    private Context context;
    /* 更新进度条 */
    private RingProgressBar mProgress;
    private Dialog mDownloadDialog;
    private boolean cancelUpdate = false;
    private String mSavePath = "";
    private String apkName = "new.apk";

    public UpdateManager(Context context) {
        this.context = context;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    //更新进度
                    int progress = (int) msg.obj;
                    mProgress.setProgress(progress);
                    break;
                case FINISH_DOWNLOAD:
                    //完成下载
                    installAPK();//安装
                    break;
                case DOWNLOAD_FAILURE:
                    showDownLoadFaildDialog();
                    break;
            }
        }
    };

    private AsyncTask<String, Integer, Boolean> downLoadTask = new AsyncTask<String, Integer, Boolean>() {

        @Override
        protected Boolean doInBackground(String... params) {
            String downloadUrl = params[0];
            String appName = params[1];
            InputStream is = null;
            File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + appName);
            mSavePath = apkFile.getAbsolutePath();
            if (apkFile.exists()) {
                apkFile.delete();
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(downloadUrl)
                    .addHeader("Accept-Encoding", "identity")
                    .build();

            try {
                FileOutputStream fos = new FileOutputStream(apkFile);

                Response response = client.newCall(request).execute();

                if (response != null && response.isSuccessful()) {
                    is = response.body().byteStream();
                    long contentLength = response.body().contentLength();
                    byte[] b = new byte[1024];
                    int total = 0;
                    int len = 0;
                    while ((len = is.read(b)) != -1) {
                        if (cancelUpdate) {
                            return false;
                        } else {
                            total += len;
                            fos.write(b, 0, len);
                            int progress = (int) (total * 100 / contentLength);
                            publishProgress(progress);//更新进度
                        }
                    }
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }


            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Message message = new Message();
            if (aBoolean && !cancelUpdate) {
                message.what = FINISH_DOWNLOAD;
            } else if (!aBoolean && !cancelUpdate) {
                message.what = DOWNLOAD_FAILURE;
            } else {
                return;
            }
            handler.handleMessage(message);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Message message = new Message();
            message.what = UPDATE_PROGRESS;
            message.obj = values[0];
            handler.handleMessage(message);
        }
    };

    /**
     * 检查更新
     */
    public void checkUpdate() {
        APIServiceManager.createRetrofitServer(UpdateModel.class)
                .getAppInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<Result>() {
                    @Override
                    public void onSuccess(Result value) {
//                        AppInfo appInfo = value.getData();
//                        int currentVersion = getVersionCode();
//                        if (currentVersion < appInfo.getAppVersion()) {
//                            showUpdateDialog();
//                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }

    /**
     * 版本更新提示框
     */
    public void showUpdateDialog() {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(RUtil.getString(context, R.string.title_app_update));
        dialog.setContentText(RUtil.getString(context, R.string.content_app_update));
        dialog.setCancelText(RUtil.getString(context, R.string.update_later));
        dialog.setConfirmText(RUtil.getString(context, R.string.update_now));
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                showDownLoadDialog();
            }
        });
        dialog.show();
    }

    /**
     * 弹出下载提示框
     */
    public void showDownLoadDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_download, null);
        mProgress = view.findViewById(R.id.progress);
        final TextView cancelBtn = view.findViewById(R.id.cancelBtn);
        mDownloadDialog = new AlertDialog.Builder(context).setView(view).create();
        mDownloadDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDownloadDialog.dismiss();
                        // 设置取消状态
                        cancelUpdate = true;
                    }
                });
            }
        });

        mDownloadDialog.show();
        // 下载文件
        downloadAPK();
    }

    /**
     * 弹出下载失败提示框
     */
    private void showDownLoadFaildDialog() {
        DialogUtil.showFaildDialog(context, R.string.download_fail);
    }

    /**
     * 下载最新版本app
     */
    private void downloadAPK() {
        String serverAddress = ConfigManager.getIns().getConfig(Config.SERVER_ADDRESS);
        downLoadTask.execute(serverAddress + "downloadAPK", apkName);
    }

    /**
     * 安装app
     */
    private void installAPK() {
        File apkfile = new File(mSavePath);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        context.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取APP版本号
     *
     * @return
     */
    public int getVersionCode() {
        int versionCode = 0;
        try {
            // 获取app版本号，对应build.gradle 下 versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.itran.mvpapplication", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     * 更新信息获取接口
     */
    interface UpdateModel {
        @GET("getAppInfo")
        Observable<Result> getAppInfo();
    }

}
