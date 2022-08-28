package com.example.ocr.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.FragmentPersonBinding;
import com.example.ocr.ui.viewModel.HomeViewModel;

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