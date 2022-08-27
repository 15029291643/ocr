package com.example.ocr.ui;

import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private ActivityResultLauncher<String> mContentLauncher;
    private MutableLiveData<Uri> mUriLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Uri>> mUrisLiveData = new MutableLiveData<>();

    public void setUris(List<Uri> uris) {
        mUrisLiveData.setValue(uris);
    }

    public LiveData<List<Uri>> getUrisLiveData() {
        return mUrisLiveData;
    }

    public void setContentLauncher(ActivityResultLauncher<String> contentLauncher) {
        mContentLauncher = contentLauncher;
    }

    public LiveData<Uri> getUriLiveData() {
        return mUriLiveData;
    }

    public void setUri(Uri uri) {
        Log.e(TAG, "setUri: " + uri);
        mUriLiveData.setValue(uri);
    }

    // 获取内容
    public void contentLaunch() {
        mContentLauncher.launch("image/*");
    }

    // 获取照相
    public void cameraLaunch() {
        mContentLauncher.launch("image/*");
    }
}
