package com.example.ocr.logic.util;

import android.content.Context;
import android.os.Environment;

import com.example.ocr.MainActivity;
import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.InvoiceData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Context mContext = MainActivity.getContext();
    private static final String TAG = "RetrofitUtils";

    public RetrofitUtils(Context context) {
        mContext = context;
    }

    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(DataUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final InvoiceService mInvoiceService = mRetrofit.create(InvoiceService.class);

    public static List<InvoiceData> getInvoiceDatas(String base64) {
        List<InvoiceData> invoiceDatas = null;
        try {
            invoiceDatas = mInvoiceService.getInvoice(DataUtils.ACCESS_TOKEN, base64).execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoiceDatas;
    }


    // 必须在所有网络请求之前
    public static void getToken() throws IOException {
        DataUtils.ACCESS_TOKEN = mInvoiceService.getToken().execute().body().getData().getAccess_token();
    }

}
