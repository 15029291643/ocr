package com.example.ocr;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    private final MutableLiveData<Uri> mUriLiveData = new MutableLiveData<>();
    private final MutableLiveData<Bitmap> mBitmapLiveData = new MutableLiveData<>();

    public MutableLiveData<Uri> getUriLiveData() {
        return mUriLiveData;
    }

    public void setUri(Uri uri) {
        mUriLiveData.setValue(uri);
    }

    public LiveData<Bitmap> getBitmapLiveData() {
        return mBitmapLiveData;
    }

    public void setBitmap(Bitmap bitmap) {
        Log.e(TAG, "setBitmap: ");
        mBitmapLiveData.setValue(bitmap);
    }
}
