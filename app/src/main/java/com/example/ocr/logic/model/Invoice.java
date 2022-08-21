package com.example.ocr.logic.model;

import java.util.List;


public class Invoice {

    private int code;
    private int status;
    private String message;
    private String serialNo;
    private List<InvoiceData> data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setData(List<InvoiceData> data) {
        this.data = data;
    }

    public List<InvoiceData> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", data=" + data +
                '}';
    }
}