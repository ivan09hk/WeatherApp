package com.ivanleung.hksgweatherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ivanleung.hksgweatherapp.api.ApiCallBack;
import com.ivanleung.hksgweatherapp.api.ApiModule;
import com.ivanleung.hksgweatherapp.ui.weatherpage.WeatherFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ApiModule.getInstance(this).getWeather(new ApiCallBack() {
//            @Override
//            public void OnApiCallBack(int mApiTag, int mResult, String mResponse) {
//                Log.i("Callback", ""+mResponse+" "+mResponse);
//            }
//        });
        WeatherFragment weatherFragment = new WeatherFragment();
        replaceFragment(R.id.container, weatherFragment, weatherFragment.getTag(), false);
    }

    private void replaceFragment(int container, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment, tag);
        if (addToBackStack)
            transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }
}
