package com.example.ocr.ui.fragment;


import android.annotation.SuppressLint;
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
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.ui.viewModel.FileViewModel;
import com.example.ocr.ui.adapter.FileAdapter;
import com.example.ocr.ui.viewModel.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileFragment extends Fragment implements View.OnClickListener {


    private FragmentFileBinding binding;
    private static final String TAG = "FileFragment";
    private Context context;
    private FragmentActivity activity;
    private HomeViewModel homeViewModel;
    private FileViewModel fileViewModel;
    private FileAdapter adapter;
    private List<List<InvoiceData>> invoicesList;
    private BottomSheetDialog dialog;
    private TextView camera;
    private TextView content;
    private TextView cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = requireContext();
        activity = requireActivity();
        homeViewModel = new ViewModelProvider(activity).get(HomeViewModel.class);
        fileViewModel = new ViewModelProvider(activity).get(FileViewModel.class);

        initView();
        // 底部淡出菜单
        binding.floatingActionButton.setOnClickListener(v -> {
            dialog.show();
        });
        camera.setOnClickListener(this);
        content.setOnClickListener(this);
        cancel.setOnClickListener(this);
        // 文件列表
        adapter = new FileAdapter(fileViewModel.getFileList());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerView.setAdapter(adapter);
        // 文件更新
        homeViewModel.getUriLiveData().observe(activity, uri -> {
            fileViewModel.getUriObservable(uri)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(invoiceData -> {
                        ArrayList<List<InvoiceData>> invoicesList = new ArrayList<>(Collections.singletonList(
                                invoiceData
                        ));
                        String name = DateUtil.now() + ".xlsx";
                        ExcelUtils.create(invoicesList, name);
                        adapter.add(new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                    });
        });

        homeViewModel.getUriListLiveData().observe(activity, uris -> {
            fileViewModel.getUriLsetObservable(uris)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(invoicesList::add, Throwable::printStackTrace, () -> {
                        String name = DateUtil.now() + ".xlsx";
                        ExcelUtils.create(invoicesList, name);
                        adapter.add(new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                    });
        });
    }

    private void initView() {
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.view_camera);
        camera = dialog.findViewById(R.id.camera);
        content = dialog.findViewById(R.id.content);
        cancel = dialog.findViewById(R.id.cancel);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                dialog.cancel();
                Toast.makeText(context, "功能已关闭", Toast.LENGTH_SHORT).show();
                // viewModel.cameraLaunch();
                break;
            case R.id.content:
                dialog.cancel();
                // 重置相册获取图片列表
                invoicesList = new ArrayList<>();
                Toast.makeText(context, "功能已关闭", Toast.LENGTH_SHORT).show();
                // viewModel.contentLaunch();
                break;
            case R.id.cancel:
                dialog.cancel();
                break;
            default:
                break;
        }
    }
}