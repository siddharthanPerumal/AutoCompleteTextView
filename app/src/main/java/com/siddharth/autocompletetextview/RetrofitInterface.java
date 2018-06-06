package com.siddharth.autocompletetextview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("coupons")
    Call<ResponseData> getCoupon();

}
