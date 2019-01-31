package com.ivanleung.hksgweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ivanleung.hksgweatherapp.api.ApiCallBack;
import com.ivanleung.hksgweatherapp.api.ApiModule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiModule.getInstance(this).getWeather(new ApiCallBack() {
            @Override
            public void OnApiCallBack(int mApiTag, int mResult, String mResponse) {
                Log.i("Callback", ""+mResponse+" "+mResponse);
            }
        });
    }
}
