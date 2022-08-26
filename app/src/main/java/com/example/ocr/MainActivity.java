package com.example.ocr;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.ActivityMainBinding;
import com.example.ocr.logic.callback.UriCallback;
import com.example.ocr.logic.util.CameraUtils;
import com.example.ocr.ui.HomeActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @SuppressLint("StaticFieldLeak")
    private static Activity mActivity;
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static AppCompatActivity mAppCompatActivity;
    private static MainViewModel mViewModel;
    private static ActivityResultLauncher<String> launcher;
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

    public static MainViewModel getViewModel() {
        return mViewModel;
    }

    public static ActivityResultLauncher<String> getLauncher() {
        return launcher;
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
        // 相机工具类
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            Log.e(TAG, "onCreate: " + "launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {");
            mViewModel.setUri(uri);
        });
        mViewModel.getUriLiveData().observe(this, mBinding.mainImg::setImageURI);
        mBinding.mainBtn.setOnClickListener(v -> launcher.launch("image/*"));
        // 界面跳转
        startActivity(new Intent(this, HomeActivity.class));
    }

}