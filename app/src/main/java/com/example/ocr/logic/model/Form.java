package com.example.ocr.logic.model;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("excel")
public class Form {
    @Excel(name = "销售方识别号")
    private String s0;
    @Excel(name = "销售方名称")
    private String s1;
    @Excel(name = "购买方识别号")
    private String s2;
    @Excel(name = "购买方名称")
    private String s3;
    @Excel(name = "发票名称")
    private String s4;
    @Excel(name = "发票代码")
    private String s5;
    @Excel(name = "发票号码")
    private String s6;
    @Excel(name = "打印发票代码")
    private String s7;
    @Excel(name = "打印发票号码")
    private String s8;
    @Excel(name = "开票日期")
    private String s9;

    public Form() {
    }

    public Form(String s0, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        this.s0 = s0;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
    }

    public String getS0() {
        return s0;
    }

    public void setS0(String s0) {
        this.s0 = s0;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }

    @Override
    public String toString() {
        return "Form{" +
                "s0='" + s0 + '\'' +
                ", s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", s5='" + s5 + '\'' +
                ", s6='" + s6 + '\'' +
                ", s7='" + s7 + '\'' +
                ", s8='" + s8 + '\'' +
                ", s9='" + s9 + '\'' +
                '}';
    }
}
