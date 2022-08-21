
package com.example.ocr.logic.model;

public class Token {

    private int code;
    private TokenData data;
    private int status;
    private String serialNo;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setData(TokenData data) {
        this.data = data;
    }

    public TokenData getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    @Override
    public String toString() {
        return "Token{" +
                "code=" + code +
                ", data=" + data +
                ", status=" + status +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}