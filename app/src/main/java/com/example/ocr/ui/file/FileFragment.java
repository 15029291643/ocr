package com.example.ocr.ui.file;

import android.content.Context;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;
    private static final String TAG = "FileFragment";
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentFileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = requireContext();
        // 底部弹出
        ListView listView = new ListView(mContext);
        String[] modes = {"相机", "图库"};
        listView.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, modes));
        BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(listView);
        // 添加按钮
        mBinding.fileAdd.setOnClickListener(v -> {
            dialog.show();
        });
        // 获取照片
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // 拍照
                    case 0:
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