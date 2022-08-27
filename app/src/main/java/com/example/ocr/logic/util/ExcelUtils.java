package com.example.ocr.logic.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.ocr.MainActivity;
import com.example.ocr.logic.model.Form;
import com.example.ocr.logic.model.InvoiceData;
import com.example.ocr.logic.model.User;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;

// excel操作工具类
public class ExcelUtils {
    private static Context mContext = MainActivity.getContext();
    private static final String TAG = "ExcelUtils";

    public static void open(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mContext.startActivity(intent);
    }

    public static void create(List<Form> forms, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName));
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Form.class, forms);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Form InvoicesToFrom(List<InvoiceData> invoiceDatas) {
        return new Form(
                invoiceDatas.get(0).getValue(),
                invoiceDatas.get(1).getValue(),
                invoiceDatas.get(2).getValue(),
                invoiceDatas.get(3).getValue(),
                invoiceDatas.get(4).getValue(),
                invoiceDatas.get(5).getValue(),
                invoiceDatas.get(6).getValue(),
                invoiceDatas.get(7).getValue(),
                invoiceDatas.get(8).getValue(),
                invoiceDatas.get(9).getValue()
        );
    }
}
