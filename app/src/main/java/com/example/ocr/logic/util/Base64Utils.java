package com.example.ocr.logic.util;

import android.content.Context;
import android.graphics.Bitmap;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import com.example.ocr.MainActivity;

import org.apache.poi.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;

// base64转换工具类
public class Base64Utils {
    private static Context mContext = MainActivity.getContext();

    public static String fromFile(File file) {
        String encode = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            IOUtils.copy(inputStream, outputStream);
            encode = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encode;
    }


    // 将内容string转成base64
    private static String base64Encode(String content, String charsetName) {
        if (TextUtils.isEmpty(charsetName)) {
            charsetName = "UTF-8";
        }
        try {
            byte[] contentByte = content.getBytes(charsetName);
            return Base64.encodeToString(contentByte, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 将内容base64转成string
    private static String base64Decode(String content, String charsetName) {
        if (TextUtils.isEmpty(charsetName)) {
            charsetName = "UTF-8";
        }
        byte[] contentByte = Base64.decode(content, Base64.DEFAULT);
        try {
            return new String(contentByte, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void fromUri(URI uri) throws IOException {

        /*new FileInputStream(new File(new ))
        Base64.encodeToString()*/
    }

    // 文件名转base64
    public static String fileNameToBase64(String fileName) throws IOException {
        return Base64.encodeToString(new FileUtils().fileNameToBytes(fileName), Base64.DEFAULT);
    }

    // 文件输入流名转base64
    public static String inputStreamToBase64(InputStream inputStream) throws IOException {
        return Base64.encodeToString(new FileUtils().inputStreamToBytes(inputStream), Base64.DEFAULT);
    }
}
