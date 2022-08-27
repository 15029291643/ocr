package com.example.ocr.ui.file;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ocr.databinding.FragmentFileBinding;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.Base64Utils;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.logic.util.RetrofitUtils;
import com.example.ocr.ui.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;
    private static final String TAG = "FileFragment";
    private Context mContext;
    private FragmentActivity mMActivity;
    private HomeViewModel mViewModel;

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
        mMActivity = requireActivity();
        mViewModel = new ViewModelProvider(mMActivity).get(HomeViewModel.class);

        List<File> files = FileUtils.getExcelFiles();
        FileAdapter fileAdapter = new FileAdapter(files);
        mBinding.fileList.setAdapter(fileAdapter);
        mBinding.fileList.setLayoutManager(new LinearLayoutManager(mContext));
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
        // 文件更新
        mViewModel.getUriLiveData().observe(mMActivity, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                File file = FileUtils.uriToFile(uri);
                String base64 = Base64Utils.fromFile(file);
                List<InvoiceData> invoiceDatas = RetrofitUtils.getInvoiceDatas(base64);
            }
        });
        // 获取照片
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                // 拍照
                case 0:

                    break;
                    // 相册
                case 1:
                    mViewModel.contentLaunch();
                    break;
                default:
                    break;
            }
        });
    }
}