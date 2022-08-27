package com.example.ocr.ui.file;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ocr.databinding.FragmentFileBinding;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.util.ExcelUtils;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.logic.util.RetrofitUtils;
import com.example.ocr.ui.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileFragment extends Fragment {


    private com.example.ocr.databinding.FragmentFileBinding mBinding;
    private static final String TAG = "FileFragment";
    private Context mContext;
    private FragmentActivity mActivity;
    private HomeViewModel mViewModel;
    private FileAdapter mAdapter;

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
        mBinding.fileList.setAdapter(mAdapter);
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
        mViewModel.getUriLiveData().observe(mActivity, uri -> {
            Observable.create((Observable.OnSubscribe<List<InvoiceData>>) subscriber -> {
                subscriber.onNext(RetrofitUtils.getInvoices(uri));
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(invoiceData -> {
                        ArrayList<List<InvoiceData>> invoicesList = new ArrayList<>(Arrays.asList(
                                invoiceData,
                                invoiceData,
                                invoiceData,
                                invoiceData
                        ));
                        String name = DateUtil.now() + ".xlsx";
                        ExcelUtils.create(invoicesList, name);
                        mAdapter.add(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                    });
        });
        /*mViewModel.getUrisLiveData().observe(mActivity, uris -> {
            Observable.from(uris)
                    .map(RetrofitUtils::getInvoices)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<InvoiceData>>() {
                        final List<List<InvoiceData>> invoicesList = new ArrayList<>();

                        @Override
                        public void onCompleted() {
                            Log.e(TAG, "onCompleted: ");
                            String name = DateUtil.now() + ".xlsx";
                            ExcelUtils.create(invoicesList, name);
                            mAdapter.add(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(List<InvoiceData> invoiceData) {
                            Log.e(TAG, "onNext: ");
                            invoicesList.add(invoiceData);
                        }
                    });
        });*/

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
            dialog.cancel();
        });
    }
}