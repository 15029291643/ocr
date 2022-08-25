package com.example.ocr.logic.util;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.ocr.logic.callback.BitmapCallback;
import com.example.ocr.logic.callback.UriCallback;
import com.example.ocr.logic.callback.UrisCallback;

import java.io.File;
import java.util.List;

public class PictureUtils {
    private AppCompatActivity mActivity;

    public PictureUtils(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void getUriByCamera(UriCallback callback) {
        File file = new File(mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "img_test1.jpg");
        Uri uri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".provider", file);
        mActivity.registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> callback.getUri(uri)).launch(uri);
    }

    public ActivityResultLauncher<Void> getLaunchByCamera(BitmapCallback callback) {
        return mActivity.registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), callback::getBitmap);
    }

    public ActivityResultLauncher<String> getLaunchByContent(UriCallback callback) {
        return mActivity.registerForActivityResult(new ActivityResultContracts.GetContent(), callback::getUri);
    }

    public ActivityResultLauncher<String> getLaunchByContent(UrisCallback callback) {
        return mActivity.registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), callback::getUris);
    }

    public void launch(ActivityResultLauncher<String> launcher) {
        launcher.launch("image/*");
    }

}
