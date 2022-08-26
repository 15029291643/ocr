package com.example.ocr.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.ocr.MainViewModel;
import com.example.ocr.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding mBinding;
    private MainViewModel mViewModel;
    private static ActivityResultLauncher<String> launcher;

    public static ActivityResultLauncher<String> getLauncher() {
        return launcher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        String[] tags = {"文件", "个人"};
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        new TabLayoutMediator(mBinding.homeTabLayout, mBinding.homeViewPager, (tab, position) -> {
            tab.setText(tags[position]);
        }).attach();
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            Log.e(TAG, "onCreate: " + "launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {");
            mViewModel.setUri(uri);
        });
    }
}