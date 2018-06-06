package com.siddharth.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    AutoCompleteTextView autoCompleteTextView;
    MyCustomAdapter myCustomAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.acTextView);
        autoCompleteTextView.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 3) {
            getAllDate();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void getAllDate() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.zoftino.com/api/").
                addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ResponseData> responseDataCall = retrofitInterface.getCoupon();
        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                arrayList = new ArrayList<>();
                ArrayList<ResponseData.Coupen> arrayList1 = response.body().getCoupons();
                for (ResponseData.Coupen coupon : arrayList1) {
                    arrayList.add(coupon.getCoupon());
                }
                myCustomAdapter = new MyCustomAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line
                        , arrayList);
                autoCompleteTextView.setAdapter(myCustomAdapter);
                autoCompleteTextView.showDropDown();
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });

    }
}
