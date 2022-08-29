package com.example.ocr.logic.network;

import static com.example.ocr.logic.util.DataUtils.ACCESS_TOKEN;

import android.content.Context;
import android.util.Log;

import com.example.ocr.logic.callback.StringCallback;
import com.example.ocr.logic.util.DataUtils;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.ui.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    private static Context mContext = MyApplication.getContext();
    private static final OkHttpClient mClient = new OkHttpClient();
    private static final String TAG = "OkHttpUtils";
    private static final String BAIDU_URL = "https://www.baidu.com";

    public static String getTokenTest() {
        String url = DataUtils.BASE_URL +
                "getAccessToken?accessKey="
                + DataUtils.ACCESS_KEY
                + "&accessSecret="
                + DataUtils.ACCESS_SECRET;
        Request request = new Request.Builder()
                .url(url)
                .build();
        String string = "";
        try {
            string = mClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getAccessToken: " + string);
        return string;
    }

    public static String getInvoiceTest()  {
        RequestBody body = new FormBody.Builder()
                .add("token", DataUtils.ACCESS_TOKEN)
                .add("imgBase64", FileUtils.getBase64Test())
                .build();
        Request request = new Request.Builder()
                .url(DataUtils.BASE_URL + "ocr/vatInvoice")
                .post(body)
                .build();
        String string = "";
        try {
            string = mClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getInvoiceTest: " + string);
        return string;
    }

}
