package com.example.ocr.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.R;
import com.example.ocr.databinding.ActivityHomeBinding;
import com.example.ocr.ui.adapter.HomeAdapter;
import com.example.ocr.ui.viewModel.HomeViewModel;
import com.google.android.material.navigation.NavigationBarView;
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

        // 禁止滑动
        mBinding.homeViewPager.setUserInputEnabled(false);
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        mBinding.homeNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.:
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        break;
                }
                return false;
            }
        });
        mViewModel.setContentLauncher(registerForActivityResult(new ActivityResultContracts.GetContent(), mViewModel::setUri));
        mViewModel.setContentLauncher2(registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), mViewModel::setUris));
    }
}