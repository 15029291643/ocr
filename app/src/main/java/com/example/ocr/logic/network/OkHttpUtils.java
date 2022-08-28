package com.example.ocr.logic.network;

import android.content.Context;

import com.example.ocr.ui.activity.MainActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    private static  Context mContext = MainActivity.getContext();
    private static final OkHttpClient sClient = new OkHttpClient();
    private static final String TAG = "OkHttpUtils";
    public static final String BAIDU_URL = "https://www.baidu.com";

    // 网络请求
    private static  String getResponse(Request request) throws IOException {
        return sClient.newCall(request).execute().body().string();
    }
}
