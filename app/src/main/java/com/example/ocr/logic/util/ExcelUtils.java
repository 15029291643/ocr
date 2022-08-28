package com.example.ocr.logic.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.example.ocr.ui.activity.MainActivity;
import com.example.ocr.logic.model.Form;
import com.example.ocr.logic.model.InvoiceData;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

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


    public static void create(List<List<InvoiceData>> invoicesList, String name) {
        List<Form> forms = invoicesList.stream().map(ExcelUtils::invoicesToFrom).collect(Collectors.toList());
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), name));
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Form.class, forms);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Form invoicesToFrom(List<InvoiceData> invoices) {
        return new Form(
                invoices.get(0).getValue(),
                invoices.get(1).getValue(),
                invoices.get(2).getValue(),
                invoices.get(3).getValue(),
                invoices.get(4).getValue(),
                invoices.get(5).getValue(),
                invoices.get(6).getValue(),
                invoices.get(7).getValue(),
                invoices.get(8).getValue(),
                invoices.get(9).getValue()
        );
    }
}
