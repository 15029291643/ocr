package com.example.ocr.logic.network;

import static com.example.ocr.ui.activity.MainActivity.mContext;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.Invoice;
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
            Invoice invoice = mInvoiceService.getInvoice(DataUtils.ACCESS_TOKEN, FileUtils.uriToBase64(uri)).execute().body();
            if (invoice.getCode() == 200) {
                invoices = invoice.getData();
            } else {
                Toast.makeText(mContext, "请选择正确的图片格式", Toast.LENGTH_SHORT).show();
                throw new Exception(invoice.toString());
            }
        } catch (Exception e) {
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
