package com.example.ocr.logic.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.ocr.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    // 文件名转byte数组
    public static byte[] fileNameToBytes(String fileName) throws IOException {
        InputStream inputStream = mContext.getAssets().open(fileName);
        return inputStreamToBytes(inputStream);
    }

    public static void copy(String inputName, String outputName) throws IOException {
        InputStream inputStream = mContext.getAssets().open(inputName);
        FileOutputStream outputStream = new FileOutputStream(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), outputName));
        IoUtil.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
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
    public static List<File> getExcelFiles()  {
        File dir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return Arrays.stream(dir.listFiles()).filter(file -> file.getName().contains(".xlsx")).collect(Collectors.toList());
    }

    // 读取目录下的Excel文件名
    public static List<String> getExcelNames() throws IOException {
        return getExcelFiles().stream().map(File::getName).collect(Collectors.toList());
    }

    public static File getExcelFile() {
        return new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "xlsx_test1.xlsx");
    }

    // 网上复制
    public static File uriToFile(Uri uri) {
        File file = null;
        if (uri == null) {
            return file;
        }
        //android10以上转换
        if (uri.getScheme().equals(ContentResolver.SCHEME_FILE)) {
            file = new File(uri.getPath());
        } else if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //把文件复制到沙盒目录
            ContentResolver contentResolver = mContext.getContentResolver();
            String displayName = System.currentTimeMillis() + Math.round((Math.random() + 1) * 1000)
                    + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));
            try {
                InputStream is = contentResolver.openInputStream(uri);
                File cache = new File(mContext.getCacheDir().getAbsolutePath(), displayName);
                FileOutputStream fos = new FileOutputStream(cache);
                IoUtil.copy(is, fos);
                file = cache;
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
