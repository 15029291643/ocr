package com.example.ocr.logic.model;

public class InvoiceData {

    private String desc;
    private String value;
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "InvoiceData{" +
                "desc='" + desc + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}