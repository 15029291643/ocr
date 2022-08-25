package com.example.ocr.logic.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.ocr.logic.model.User;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;

// excel操作工具类
public class ExcelUtils {
    private Context mContext;
    private static final String TAG = "ExcelUtils";

    public ExcelUtils(Context context) {
        mContext = context;
    }

    public void open(File file) throws IOException {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mContext.startActivity(intent);
    }

    public void openTest() throws IOException {
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "xlsx_test2.xlsx");
        open(file);
    }

    public void write(List<User> users, OutputStream outputStream) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), User.class, users);
        workbook.write(outputStream);
        workbook.close();
    }

    public void writeTest() throws IOException {
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "xlsx_test2.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        write(DataUtils.getUsers(), outputStream);
        outputStream.close();
    }

    public void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        IoUtil.copy(inputStream, outputStream);
    }

    public void copyTest() throws IOException {
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "txt_test2.txt");
        InputStream inputStream = mContext.getAssets().open("txt_test.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        copy(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
    }
}
