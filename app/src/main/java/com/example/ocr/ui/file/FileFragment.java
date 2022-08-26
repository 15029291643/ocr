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

import java.util.ArrayList;
import java.util.Arrays;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentFileBinding.inflate(inflater, container, false);
        return  mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] datas = {"file", "file.txt", "file.txt", "file.txt", "file.txt", "file.txt", "file.txt", "file.txt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, datas);
        mBinding.fileList.setAdapter(adapter);
        mBinding.fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view).getText().toString();
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}