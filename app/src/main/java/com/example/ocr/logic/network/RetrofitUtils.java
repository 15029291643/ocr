package com.example.ocr.logic.network;

import android.net.Uri;
import android.util.Log;

import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.DataUtils;
import com.example.ocr.logic.util.FileUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";

    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(DataUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final InvoiceService mInvoiceService = mRetrofit.create(InvoiceService.class);

    public static List<InvoiceData> getInvoices(Uri uri) {
        List<InvoiceData> invoices = null;
        try {
            invoices = mInvoiceService.getInvoice(DataUtils.ACCESS_TOKEN, FileUtils.uriToBase64(uri)).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoices;
    }


    // 必须在所有网络请求之前
    public static void initToken() {
        try {
            // DataUtils.ACCESS_TOKEN = mInvoiceService.getToken().execute().body().getData().getAccess_token();
            String token = null;
            token = mInvoiceService.getToken().execute().body().getData().getAccess_token();
            Log.e(TAG, "initToken: " + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
