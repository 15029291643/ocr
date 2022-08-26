package com.example.ocr;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.Base64Utils;
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.logic.util.RetrofitUtils;
import com.example.ocr.ui.CameraActivity;
import com.example.ocr.ui.HomeActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        // 发票识别测试
        /*new Thread(() -> {
            try {
                InputStream inputStream = getAssets().open("img_test.png");
                List<InvoiceData> invoices = new RetrofitUtils(this).getInvoice(inputStream);
                for (InvoiceData invoiceData : invoices) {
                    Log.e(TAG, "onCreate: " + invoiceData.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
        // IO测试
        /*try {
            ExcelUtils excelUtils = new ExcelUtils(this);
            excelUtils.writeTest();
            excelUtils.openTest();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        startActivity(new Intent(this, HomeActivity.class));
    }

    public static Context getContext() {
        return mContext;
    }
}