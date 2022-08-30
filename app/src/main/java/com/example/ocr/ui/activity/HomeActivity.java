package com.example.ocr.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.R;
import com.example.ocr.databinding.ActivityHomeBinding;
import com.example.ocr.ui.adapter.HomeAdapter;
import com.example.ocr.ui.viewModel.HomeViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {
    private static AppCompatActivity activity;
    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // 侧滑菜单和导航栏联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includeContent.toolbar, R.string.app_name, R.string.app_name);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // 侧滑菜单点击时间
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_file:
                        binding.drawerLayout.close();
                        binding.includeContent.bottomNavigationView.setSelectedItemId(R.id.nav_file);
                        break;
                    case R.id.item_camera:
                        binding.drawerLayout.close();
                        binding.includeContent.bottomNavigationView.setSelectedItemId(R.id.nav_camera);
                        break;
                    case R.id.item_me:
                        binding.drawerLayout.close();
                        binding.includeContent.bottomNavigationView.setSelectedItemId(R.id.nav_me);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        // 初始化界面Title
        binding.includeContent.toolbar.setTitle(viewModel.getTitles()[0]);
        // ViewPager禁止滑动
        binding.includeContent.viewPager.setUserInputEnabled(false);
        binding.includeContent.viewPager.setAdapter(new HomeAdapter(this));
        // 底部导航栏点击事件
        binding.includeContent.bottomNavigationView.setOnItemSelectedListener(item -> {
            int position = viewModel.getItemIdList().indexOf(item.getItemId());
            // 设置导航栏的Title
            binding.includeContent.toolbar.setTitle(viewModel.getTitles()[position]);
            // 跳转界面
            binding.includeContent.viewPager.setCurrentItem(position, false);
            return true;
        });
        // 初始化照片获取
        viewModel.initLauncher();
    }

    public static AppCompatActivity getActivity() {
        return activity;
    }
}