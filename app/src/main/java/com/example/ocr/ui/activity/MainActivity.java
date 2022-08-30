package com.example.ocr.ui.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ocr.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static AppCompatActivity appCompatActivity;
    private ActivityMainBinding binding;

    public static Context getContext() {
        return context;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        activity = this;
        appCompatActivity = this;

        startActivity(new Intent(this, HomeActivity.class));
    }

}