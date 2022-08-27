package com.example.ocr;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.ActivityMainBinding;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.logic.util.RetrofitUtils;
import com.example.ocr.ui.HomeActivity;

import java.io.IOException;

import rx.Observable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @SuppressLint("StaticFieldLeak")
    private static Activity mActivity;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static AppCompatActivity mAppCompatActivity;
    private static MainViewModel mViewModel;
    private ActivityMainBinding mBinding;

    public static Context getContext() {
        return mContext;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public static AppCompatActivity getAppCompatActivity() {
        return mAppCompatActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = this;
        mActivity = this;
        mAppCompatActivity = this;
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // new Thread(RetrofitUtils::initToken).start();
        startActivity(new Intent(this, HomeActivity.class));
    }

}