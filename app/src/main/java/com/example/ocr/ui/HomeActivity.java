package com.example.ocr.ui;


import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.R;
import com.example.ocr.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

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

        // 禁止滑动
        mBinding.homeViewPager.setUserInputEnabled(false);
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        String[] tags = {"文件", "个人"};
        int[] icons = {R.drawable.ic_home_file2, R.drawable.ic_home_person};
        new TabLayoutMediator(mBinding.homeTabLayout, mBinding.homeViewPager, (tab, position) -> {
            tab.setText(tags[position]);
            tab.setIcon(icons[position]);
        }).attach();
        mViewModel.setContentLauncher(registerForActivityResult(new ActivityResultContracts.GetContent(), mViewModel::setUri));
        mViewModel.setContentLauncher2(registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), mViewModel::setUris));
    }
}