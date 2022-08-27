package com.example.ocr.ui.person;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.FragmentPersonBinding;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.logic.util.RetrofitUtils;
import com.example.ocr.ui.HomeViewModel;

import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.hutool.bloomfilter.bitMap.BitMap;
import cn.hutool.core.io.IoUtil;

public  class PersonFragment extends Fragment {

    private FragmentPersonBinding mBinding;
    private HomeViewModel mViewModel;
    private static final String TAG = "PersonFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = com.example.ocr.databinding.FragmentPersonBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        mViewModel.getUriLiveData().observe(requireActivity(), uri -> {
            mBinding.personImg.setImageURI(uri);
        });
        mBinding.personBtn.setOnClickListener(v -> mViewModel.contentLaunch());
    }
}