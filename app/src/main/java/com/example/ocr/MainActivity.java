package com.example.ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.Base64Utils;
import com.example.ocr.logic.util.OkHttpUtils;
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
    }

    public static Context getContext() {
        return mContext;
    }
}