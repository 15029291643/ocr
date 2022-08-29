package com.example.ocr.logic.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.webkit.MimeTypeMap;

import com.example.ocr.ui.activity.MainActivity;

import org.apache.poi.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.io.IoUtil;

// 文件工具类
public class FileUtils {
    private static Context mContext = MainActivity.getContext();
    private static final String TAG = "FileUtils";


    // 读取目录下的Excel文件
    public static List<File> getExcelFileList()  {
        File dir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return Arrays.stream(dir.listFiles()).filter(file -> file.getName().contains(".xlsx")).collect(Collectors.toList());
    }

    // 测试
    public static InputStream getInputStreamTest() {
        InputStream inputStream = null;
        try {
            inputStream = mContext.getAssets().open("img_test2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static String getBase64Test() {
        return inputStreamToBase64(getInputStreamTest());
    }

    // 测试
    public static String inputStreamToBase64(InputStream inputStream) {
        String encode = "";
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            IOUtils.copy(inputStream, outputStream);
            encode = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encode;
    }

    public static String uriToBase64(Uri uri) {
        File file = FileUtils.uriToFile(uri);
        String encode = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            encode = inputStreamToBase64(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encode;
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
