package com.example.ocr.logic.util;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.ocr.MainActivity;
import com.example.ocr.MainViewModel;
import com.example.ocr.logic.callback.BitmapCallback;
import com.example.ocr.logic.callback.UriCallback;
import com.example.ocr.logic.callback.UrisCallback;

import java.io.File;

// 相机工具类，提供launcher实例
public class CameraUtils {
    private static final AppCompatActivity mActivity = MainActivity.getAppCompatActivity();
    private static final MainViewModel mViewModel = MainActivity.getViewModel();
    public static ActivityResultLauncher<Uri> mGetUriFromCameraLauncher;
    public static ActivityResultLauncher<Void> mGetBitmapFromCameraLauncher;
    public static ActivityResultLauncher<String> mGetUriFromContentLauncher;
    public static ActivityResultLauncher<String> mGetUrisFromContentLauncher;


    public static void init() {
        initGetBitmapFromCameraLauncher();
    }

    private static void initGetUriFromCameraLauncher(Uri uri, UriCallback callback) {
        mGetUriFromCameraLauncher = mActivity.registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> callback.getUri(uri));
    }

    private static void initGetBitmapFromCameraLauncher() {
        mGetBitmapFromCameraLauncher = mActivity.registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), mViewModel::setBitmap);
    }

    private static void initGetUriFromContentLauncher(UriCallback callback) {
        mGetUriFromContentLauncher = mActivity.registerForActivityResult(new ActivityResultContracts.GetContent(), callback::getUri);
    }

    private static void initGetUrisFromContentLauncher(UrisCallback callback) {
        mGetUrisFromContentLauncher = mActivity.registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), callback::getUris);
    }

    public static void launchToCamera(Uri uri, ActivityResultLauncher<Uri> launcher) {
        launcher.launch(uri);
    }

    public static void launchToCamera(ActivityResultLauncher<Void> launcher) {
        launcher.launch(null);
    }

    public static void launchToContent(ActivityResultLauncher<String> launcher) {
        launcher.launch("image/*");
    }

    public static Uri getUriForFile() {
        File file = new File(mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "img_test1.jpg");
        return FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".provider", file);
    }
}