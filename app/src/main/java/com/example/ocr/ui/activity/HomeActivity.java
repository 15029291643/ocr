package com.example.ocr.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.R;
import com.example.ocr.databinding.ActivityHomeBinding;
import com.example.ocr.ui.adapter.HomeAdapter;
import com.example.ocr.ui.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding mBinding;
    private HomeViewModel mViewModel;
    private ArrayList<Integer> mIds;
    private String[] mTitles;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initData();
        // DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.includeContent.toolbar,  R.string.app_name, R.string.app_name);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Toolbar
        mBinding.includeContent.toolbar.setTitle(mTitles[0]);
        // ViewPager2
        mBinding.includeContent.viewPager.setUserInputEnabled(false);
        mBinding.includeContent.viewPager.setAdapter(new HomeAdapter(this));
        // BottomNavigationView
        mBinding.includeContent.bottomNavigationView.setOnItemSelectedListener(item -> {
            int position = mIds.indexOf(item.getItemId());
            // Toolbar
            mBinding.includeContent.toolbar.setTitle(mTitles[position]);
            // ViewPager2
            mBinding.includeContent.viewPager.setCurrentItem(position, false);
            return true;
        });
        // registerForActivityResult
        mViewModel.setContentLauncher2(registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), mViewModel::setUris));

        mViewModel.setUriLauncher(registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> mViewModel.setUri(mViewModel.getTempUri())));
    }

    private void initData() {
        mIds = new ArrayList<>(Arrays.asList(
                R.id.nav_file,
                R.id.nav_camera,
                R.id.nav_me
        ));
        mTitles = new String[]{"文件", "相机", "我的"};
    }
}