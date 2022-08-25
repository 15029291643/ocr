package com.example.ocr;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.Base64Utils;
import com.example.ocr.logic.util.RetrofitUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        new Thread(() -> {

            List<InvoiceData> invoice = RetrofitUtils.getInvoice(Base64Utils.fileToBase64("img_test2.png"));
            for (InvoiceData invoiceData : invoice) {
                Log.e(TAG, "run: " + invoiceData);
            }
        }).start();
    }

    public static Context getContext() {
        return mContext;
    }
}