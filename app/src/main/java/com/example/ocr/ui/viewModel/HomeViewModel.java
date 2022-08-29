package com.example.ocr.ui.viewModel;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ocr.ui.MyApplication;

import java.io.File;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final Context mContext = MyApplication.getContext();
    private static final String TAG = "HomeViewModel";
    private ActivityResultLauncher<String> mContentLauncher;
    private ActivityResultLauncher<String> mContentLauncher2;
    private MutableLiveData<Uri> uri = new MutableLiveData<>();
    private MutableLiveData<List<Uri>> mUrisLiveData = new MutableLiveData<>();
    private ActivityResultLauncher<Uri> uriLauncher;

    public void setUriLauncher(ActivityResultLauncher<Uri> uriLauncher) {
        this.uriLauncher = uriLauncher;
    }

    public void uriLauncherLaunch() {
        uriLauncher.launch(getTempUri());
    }

    public void setUris(List<Uri> uris) {
        mUrisLiveData.setValue(uris);
    }

    public LiveData<List<Uri>> getUrisLiveData() {
        return mUrisLiveData;
    }

    public void setContentLauncher(ActivityResultLauncher<String> contentLauncher) {
        mContentLauncher = contentLauncher;
    }

    public void setContentLauncher2(ActivityResultLauncher<String> contentLauncher) {
        mContentLauncher2 = contentLauncher;
    }

    public Uri getTempUri() {
        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "img_test1.jpg");
        return FileProvider.getUriForFile(mContext, "com.example.ocr" + ".provider", file);
    }

    public LiveData<Uri> getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        Log.e(TAG, "setUri: " + uri);
        this.uri.setValue(uri);
    }

    // 获取内容
    public void contentLaunch() {
        mContentLauncher.launch("image/*");

    }

    // 获取内容
    public void contentLaunch2() {
        mContentLauncher2.launch("image/*");
    }

}
