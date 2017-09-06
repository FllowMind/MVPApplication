package com.itran.mvpapplication.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.itran.mvpapplication.R;


/**
 * 声音工具类
 * Created by 淋雨又调皮 on 2017/8/15.
 */

public class SoundUtil {

    private static SoundPool soundPool;

    /**
     * 推荐放在application中初始化
     * @param context
     */
    public static void initSoundPool(Context context){
        //初始化声音池
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(context, R.raw.msg, 1);
    }


    /**
     * 播放消息提醒
     */
    public static void playMsgTip() {
        if(soundPool!=null){
            soundPool.play(1, 1, 1, 0, 0, 1);
        }
    }

}
