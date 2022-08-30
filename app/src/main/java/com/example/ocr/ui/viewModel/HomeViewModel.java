package com.example.ocr.ui.viewModel;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ocr.R;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.network.RetrofitUtils;
import com.example.ocr.logic.util.FileUtils;
import com.example.ocr.ui.MyApplication;
import com.example.ocr.ui.activity.HomeActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private Context context = MyApplication.getContext();
    private AppCompatActivity activity = HomeActivity.getActivity();
    // 拍照获取照片返回
    private MutableLiveData<Uri> uriLiveData = new MutableLiveData<>();
    // 相册获取照片返回
    private MutableLiveData<List<Uri>> uriListLiveData = new MutableLiveData<>();
    // 通过拍照获取照片
    private ActivityResultLauncher<Uri> cameraLauncher;
    // 通过相册获取照片
    private ActivityResultLauncher<String> contentLauncher;
    // 主页底部导航栏菜单id
    private ArrayList<Integer> itemIdList = new ArrayList<>(Arrays.asList(
            R.id.nav_file,
            R.id.nav_camera,
            R.id.nav_me
    ));
    // 主页Toolbar的Title
    private String[] titles = {"文件", "相机", "我的"};

    // 初始化发射请求，必须在Activity的OnCreate中调用
    public void initLauncher() {
        // 相机拍照
        cameraLauncher = activity.registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                uriLiveData.setValue(getUri());
            }
        });
        // 相册选择
        contentLauncher = activity.registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> result) {
                uriListLiveData.setValue(result);
            }
        });
    }

    // 相机返回
    public LiveData<Uri> getUriLiveData() {
        return uriLiveData;
    }

    // 相册返回
    public LiveData<List<Uri>> getUriListLiveData() {
        return uriListLiveData;
    }

    // 被相机返回调用
    private Uri getUri() {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "img_test1.jpg");
        return FileProvider.getUriForFile(context, "com.example.ocr" + ".provider", file);
    }


    // 开始从相加获取
    public void cameraLaunch() {
        cameraLauncher.launch(getUri());
    }

    // 开始从相册获取
    public void contentLaunch() {
        contentLauncher.launch("image/*");
    }

    // 顶部导航栏title
    public String[] getTitles() {
        return titles;
    }

    // 底部导航栏id
    public ArrayList<Integer> getItemIdList() {
        return itemIdList;
    }


}
