package com.example.ocr.logic.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.example.ocr.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.io.IoUtil;

// 文件工具类
public class FileUtils {
    private static Context mContext = MainActivity.getContext();
    private static final String TAG = "FileUtils";

    public static AssetManager getAssetManager() {
        return mContext.getAssets();
    }

    // 文件名转byte数组
    public static byte[] fileNameToBytes(String fileName) throws IOException {
        InputStream inputStream = getAssetManager().open(fileName);
        return inputStreamToBytes(inputStream);
    }

    // 文件转byte数组
    public static byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IoUtil.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
        return outputStream.toByteArray();
    }

    // 读取目录下的Excel文件
    public static List<File> getExcelFiles() throws IOException {
        File dir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return Arrays.stream(dir.listFiles()).filter(file -> file.getName().contains(".xlsx")).collect(Collectors.toList());
    }

    // 读取目录下的Excel文件名
    public static List<String> getExcelNames() throws IOException {
        return getExcelFiles().stream().map(File::getName).collect(Collectors.toList());
    }

    public static File getExcelFile() throws IOException {
        return new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "xlsx_test1.xlsx");
    }
}
