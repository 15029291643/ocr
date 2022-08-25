package com.example.ocr.logic.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.hutool.core.io.IoUtil;

// 文件工具类
public class FileUtils {
    private Context mContext;
    private static final String TAG = "FileUtils";

    public FileUtils(Context context) {
        this.mContext = context;
    }

    public AssetManager getAssetManager() {
        return mContext.getAssets();
    }

    // 文件名转byte数组
    public byte[] fileNameToBytes(String fileName) throws IOException {
        InputStream inputStream = getAssetManager().open(fileName);
        return inputStreamToBytes(inputStream);
    }

    // 文件转byte数组
    public byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IoUtil.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
        return outputStream.toByteArray();
    }
}
