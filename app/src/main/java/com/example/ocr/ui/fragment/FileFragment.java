package com.example.ocr.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ocr.R;
import com.example.ocr.databinding.FragmentFileBinding;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.network.RetrofitUtils;
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.ui.adapter.FileAdapter;
import com.example.ocr.ui.viewModel.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileFragment extends Fragment {


    private FragmentFileBinding mBinding;
    private static final String TAG = "FileFragment";
    private Context mContext;
    private FragmentActivity mActivity;
    private HomeViewModel mViewModel;
    private FileAdapter mAdapter;
    private List<List<InvoiceData>> invoicesList;

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
        mActivity = requireActivity();
        mViewModel = new ViewModelProvider(mActivity).get(HomeViewModel.class);

        List<File> files = FileUtils.getExcelFileList();
        mAdapter = new FileAdapter(files);
        mBinding.fileList.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.fileList.setAdapter(mAdapter);

        BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(R.layout.view_camera);
        // 添加按钮
        mBinding.fileAdd.setOnClickListener(v -> {
            dialog.show();
        });
        TextView camera = dialog.findViewById(R.id.file_camera);
        TextView content = dialog.findViewById(R.id.file_content);
        TextView cancel = dialog.findViewById(R.id.file_cancel);
        // 选择图片
        camera.setOnClickListener(v -> {
            dialog.cancel();
            Toast.makeText(mContext, "功能已关闭", Toast.LENGTH_SHORT).show();
            // mViewModel.uriLauncherLaunch();
        });
        // 选择图片
        content.setOnClickListener(v -> {
            dialog.cancel();
            invoicesList = new ArrayList<>();
            mViewModel.contentLaunch2();
            Toast.makeText(mContext, "content", Toast.LENGTH_SHORT).show();
        });
        // 选择图片
        cancel.setOnClickListener(v -> {
            dialog.cancel();
        });
        // 文件更新
        mViewModel.getUri().observe(mActivity, uri -> {
            Observable.create((Observable.OnSubscribe<List<InvoiceData>>) subscriber -> {
                        subscriber.onNext(RetrofitUtils.getInvoices(uri));
                    }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(invoiceData -> {
                        ArrayList<List<InvoiceData>> invoicesList = new ArrayList<>(Collections.singletonList(
                                invoiceData
                        ));
                        String name = DateUtil.now() + ".xlsx";
                        ExcelUtils.create(invoicesList, name);
                        mAdapter.add(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                    });
        });

        mViewModel.getUrisLiveData().observe(mActivity, uris -> {
            Observable.from(uris)
                    .map(RetrofitUtils::getInvoices)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(invoicesList::add, Throwable::printStackTrace, () -> {
                        String name = DateUtil.now() + ".xlsx";
                        ExcelUtils.create(invoicesList, name);
                        mAdapter.add(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                    });
        });
    }

}