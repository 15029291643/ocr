package com.example.ocr.logic.util;

import android.content.Context;

import com.example.ocr.logic.dao.InvoiceService;
import com.example.ocr.logic.model.InvoiceData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private Context mContext;
    private static final String TAG = "RetrofitUtils";

    public RetrofitUtils(Context context) {
        mContext = context;
    }

    private final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(DataUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final InvoiceService mInvoiceService = RETROFIT.create(InvoiceService.class);

    private List<InvoiceData> _getInvoice(String base64) throws IOException {
        return mInvoiceService.getInvoice(DataUtils.ACCESS_TOKEN, base64).execute().body().getData();
    }

    // inputStream -> List<InvoiceData>
    public List<InvoiceData> getInvoice(InputStream inputStream) throws IOException {
        return _getInvoice(new Base64Utils(mContext).inputStreamToBase64(inputStream));
    }

    // 必须在所有网络请求之前
    public void getToken() throws IOException {
        DataUtils.ACCESS_TOKEN = mInvoiceService.getToken().execute().body().getData().getAccess_token();
    }

}
