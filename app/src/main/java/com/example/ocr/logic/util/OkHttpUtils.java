package com.example.ocr.logic.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Base64;
import android.util.Log;

import com.example.ocr.MainActivity;
import com.example.ocr.logic.callback.StringCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
