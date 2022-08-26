package com.example.ocr.logic.util;

import android.content.Context;

import com.example.ocr.MainActivity;
import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.InvoiceData;

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

    private static List<InvoiceData> _getInvoice(String base64) throws IOException {
        return mInvoiceService.getInvoice(DataUtils.ACCESS_TOKEN, base64).execute().body().getData();
    }

    // inputStream -> List<InvoiceData>
    public static List<InvoiceData> getInvoice(InputStream inputStream) throws IOException {
        return _getInvoice(Base64Utils.inputStreamToBase64(inputStream));
    }

    // 必须在所有网络请求之前
    public static void getToken() throws IOException {
        DataUtils.ACCESS_TOKEN = mInvoiceService.getToken().execute().body().getData().getAccess_token();
    }

}
