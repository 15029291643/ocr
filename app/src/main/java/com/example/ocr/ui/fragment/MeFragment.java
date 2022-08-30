package com.example.ocr.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ocr.databinding.FragmentMeBinding;

import com.example.ocr.ui.viewModel.HomeViewModel;

public  class MeFragment extends Fragment {

    private FragmentMeBinding binding;
    private HomeViewModel viewModel;
    private static final String TAG = "PersonFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.getUriLiveData().observe(requireActivity(), uri -> {
            // mBinding.personImg.setImageURI(uri);
        });
        // mBinding.personBtn.setOnClickListener(v -> mViewModel.contentLaunch());
    }
}