package com.example.ocr.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ocr.MainActivity;
import com.example.ocr.MainViewModel;
import com.example.ocr.R;
import com.example.ocr.databinding.FragmentPersonBinding;
import com.example.ocr.logic.util.CameraUtils;

public class PersonFragment extends Fragment {

    private FragmentPersonBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentPersonBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mViewModel.getUriLiveData().observe(requireActivity(), mBinding.personImg::setImageURI);
        mBinding.personBtn.setOnClickListener(v -> HomeActivity.getLauncher().launch("image/*"));
    }
}