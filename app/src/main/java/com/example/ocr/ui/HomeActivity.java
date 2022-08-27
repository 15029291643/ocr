package com.example.ocr.ui;


import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding mBinding;
    private HomeViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        String[] tags = {"文件", "个   人"};
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        new TabLayoutMediator(mBinding.homeTabLayout, mBinding.homeViewPager, (tab, position) -> tab.setText(tags[position])).attach();
        mViewModel.setContentLauncher(registerForActivityResult(new ActivityResultContracts.GetContent(), mViewModel::setUri));
    }
}