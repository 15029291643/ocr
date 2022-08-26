package com.example.ocr.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ocr.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    private com.example.ocr.databinding.ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        String[] tags = {"添加", "学习", "个人"};
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        new TabLayoutMediator(mBinding.homeTabLayout, mBinding.homeViewPager, (tab, position) -> {
            tab.setText(tags[position]);
        }).attach();
    }
}