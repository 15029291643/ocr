package com.example.ocr.logic.util;

import android.util.Log;

import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.model.Token;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(StringUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final InvoiceService INVOICE_SERVICE = RETROFIT.create(InvoiceService.class);

    public static List<InvoiceData> getInvoice(String imgBase64) {
        List<InvoiceData> dataList = null;
        try {
            dataList = INVOICE_SERVICE.getInvoice(StringUtils.ACCESS_TOKEN, imgBase64).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // 必须在所有网络请求之前
    public static void getToken() {
        try {
            StringUtils.ACCESS_TOKEN = INVOICE_SERVICE.getToken().execute().body().getData().getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
