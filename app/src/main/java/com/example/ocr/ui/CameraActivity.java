package com.example.ocr.ui;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.ocr.databinding.ActivityCameraBinding;
import com.example.ocr.logic.callback.UriCallback;
import com.example.ocr.logic.util.DataUtils;
import com.example.ocr.logic.util.PictureUtils;

import java.io.File;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "CameraActivity";
    ActivityCameraBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        PictureUtils pictureUtils = new PictureUtils(this);
        Uri uri = pictureUtils.getUri();
        ActivityResultLauncher<Uri> launchToCamera = pictureUtils.getLaunchToCamera(uri, new UriCallback() {
            @Override
            public void getUri(Uri uri) {
                mBinding.cameraImg.setImageURI(uri);
            }
        });
        mBinding.cameraBtn.setOnClickListener(view -> {
            pictureUtils.launchToCamera(uri, launchToCamera);
        });
    }

}