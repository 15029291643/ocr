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
import android.widget.ListView;

import com.example.ocr.databinding.FragmentFileBinding;
import com.example.ocr.logic.util.CameraUtils;
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.logic.util.FileUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;
    private static final String TAG = "FileFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentFileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 底部弹出
        ListView listView = new ListView(getContext());
        String[] modes = {"拍照", "图库"};
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, modes));
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(listView);
        // 添加按钮
        mBinding.fileAdd.setOnClickListener(v -> {
            dialog.show();
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // 拍照
                    case 0:
                        CameraUtils.launchToCamera(CameraUtils.mGetBitmapFromCameraLauncher);
                        break;
                        // 相册
                    case 1:

                        break;
                    default:
                        break;
                }
            }
        });
    }
}