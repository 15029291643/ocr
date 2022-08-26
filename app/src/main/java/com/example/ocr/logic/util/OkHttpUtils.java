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
    public static final String ACCESS_KEY = "APPID_z13R556Rr0404437";
    public static final String ACCESS_SECRET = "458ab1902767da4c834026266435464c";
    private static final String ACCESS_TOKEN = "77bd6cc4814b37ca33680249277765850590237fd836ff845c83d8a889f8297b25a8aef5910faabe52820adc15737414f7a4a9dc7030d0aa47e7041004e6ac1bd0be30858bbf0acd833210a1935b15119acd2d47e81d5a866592d577ea34ffc6e1a2e13fd507cf8de855df4952fedd07545c596d8bb4b413308025235746ca87e6f04bfa7ede49a66ba0c54956c0e2572dcb107eeac06cea031833a1fab9294408c2092b9b73f6e5f04ee786be0388a7fbe5f0641ac6aca6e9b661a0293b1d425b5ee68fed704d2c163441465a78aa8cba3db864eb53187478ab15f40572942005a0b2ec0df639619c9880f2a5bc34e5";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    public static final String LJH_URL = "https://c.m.163.com/nc/video/home/0-10.html";
    public static final String BAIDU_URL = "https://www.baidu.com";

    private static  void getResponse(Request request, StringCallback callback) {
        sClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callback.onResponse(string);
            }
        });
    }

    public static  void getTest() {
        Request build = new Request.Builder()
                .url(LJH_URL)
                .build();
        getResponse(build, string -> Log.e(TAG, "onResponse: " + string));
    }

    public static  void getAccessToken() {
        String url = "https://ai.ybinsure.com/s/api/getAccessToken?accessKey="
                + ACCESS_KEY
                + "&accessSecret="
                + ACCESS_SECRET;
        Request request = new Request.Builder()
                .url(url)
                .build();
        getResponse(request, string -> Log.e(TAG, "getAccessToken: " + string));
    }

    public static  void getVatInvoice() throws IOException {
        String base64 = Base64Utils.fileNameToBase64("img_test.png");
        RequestBody body = new FormBody.Builder()
                .add("token", ACCESS_TOKEN)
                .add("imgBase64", base64)
                .build();
        Request request = new Request.Builder()
                .url("https://ai.ybinsure.com/s/api/ocr/vatInvoice")
                .post(body)
                .build();
        getResponse(request, string -> Log.e(TAG, "getVatInvoice: " + string));
    }


}
