package com.example.ocr.logic.dao;

import com.example.ocr.logic.model.Invoice;
import com.example.ocr.logic.model.Token;
import com.example.ocr.logic.util.DataUtils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InvoiceService {
    @GET("getAccessToken?accessKey=" + DataUtils.ACCESS_KEY + "&accessSecret=" + DataUtils.ACCESS_SECRET)
    Call<Token> getToken();

    @FormUrlEncoded
    @POST("ocr/vatInvoice")
    Call<Invoice> getInvoice(@Field("token") String token, @Field("imgBase64") String imgBase64);
}
