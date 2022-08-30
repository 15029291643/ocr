package com.example.ocr.ui.viewModel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.network.RetrofitUtils;
import com.example.ocr.logic.util.FileUtils;

import java.io.File;
import java.util.List;

import rx.Observable;

public class FileViewModel extends ViewModel {

    public List<File> getFileList() {
        return FileUtils.getExcelFileList();
    }

    public Observable<List<InvoiceData>> getUriObservable(Uri uri) {
        return Observable.create(subscriber -> {
            subscriber.onNext(RetrofitUtils.getInvoices(uri));
        });
    }

    public Observable<List<InvoiceData>> getUriLsetObservable(List<Uri> uriList) {
        return Observable.from(uriList)
                .map(RetrofitUtils::getInvoices);
    }
}
