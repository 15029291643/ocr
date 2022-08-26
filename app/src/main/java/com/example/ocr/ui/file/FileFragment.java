package com.example.ocr.ui.file;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ocr.R;
import com.example.ocr.databinding.FragmentFileBinding;
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.logic.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentFileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            FileUtils fileUtils = new FileUtils(getContext());
            ExcelUtils excelUtils = new ExcelUtils(getContext());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fileUtils.getExcelNames());
            mBinding.fileList.setAdapter(adapter);
            mBinding.fileList.setOnItemClickListener((parent, view1, position, id) -> {
                try {
                    excelUtils.open(fileUtils.getExcelFiles().get(position));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}