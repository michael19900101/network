package com.example.common.util;

import android.util.Log;

/**
 * Created by aotuman on 2018/12/14
 * describe: Log日志工具类
 */
public class L {
    public static final String TAG = "helloWorld";

    //普通版
    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    //进行类型判断
    public static void d(Object msg) {
        String string = msg.toString();
        Log.d(TAG, string);
    }
}
