package com.example.ocr.logic.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

// base64转换工具类
public class Base64Utils {
    private Context mContext;

    public Base64Utils(Context context) {
        mContext = context;
    }

    // 将内容string转成base64
    private String base64Encode(String content, String charsetName) {
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
    private String base64Decode(String content, String charsetName) {
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

    // 文件名转base64
    public String fileNameToBase64(String fileName) throws IOException {
        return Base64.encodeToString(new FileUtils(mContext).fileNameToBytes(fileName), Base64.DEFAULT);
    }

    // 文件输入流名转base64
    public String inputStreamToBase64(InputStream inputStream) throws IOException {
        return Base64.encodeToString(new FileUtils(mContext).inputStreamToBytes(inputStream), Base64.DEFAULT);
    }
}
