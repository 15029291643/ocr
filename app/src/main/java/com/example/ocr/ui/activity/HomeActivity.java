package com.example.ocr.ui.activity;


import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
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
    private int prePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        ArrayList<Integer> ids = new ArrayList<>(Arrays.asList(
                R.id.nav_file,
                R.id.nav_camera,
                R.id.nav_me
        ));
        String[] titles = {"文件", "相机", "我的"};
        mBinding.homeToolbar.setTitle(titles[0]);
        // 禁止滑动
        mBinding.homeViewPager.setUserInputEnabled(false);
        mBinding.homeViewPager.setAdapter(new HomeAdapter(this));
        mBinding.homeNavigation.setOnItemSelectedListener(item -> {
            int position = ids.indexOf(item.getItemId());
            /*if (position == 1) {

                mBinding.homeNavigation.getItem
                return true;
            }*/
            mBinding.homeToolbar.setTitle(titles[position]);
            mBinding.homeViewPager.setCurrentItem(position, false);
            prePosition = position;
            return true;
        });
        mViewModel.setContentLauncher(registerForActivityResult(new ActivityResultContracts.GetContent(), mViewModel::setUri));
        mViewModel.setContentLauncher2(registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), mViewModel::setUris));

        mViewModel.setUriLauncher(registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
            mViewModel.setUri(mViewModel.getTempUri());
        }));
    }
}