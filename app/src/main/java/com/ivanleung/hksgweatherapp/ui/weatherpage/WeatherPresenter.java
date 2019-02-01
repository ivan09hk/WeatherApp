package com.ivanleung.hksgweatherapp.ui.weatherpage;

import android.content.Context;
import android.util.Log;

import com.ivanleung.hksgweatherapp.api.ApiCallBack;
import com.ivanleung.hksgweatherapp.api.ApiModule;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;
import com.ivanleung.hksgweatherapp.data.model.WeatherModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter {

    private Disposable mDisposable;
    private WeatherModel weatherModel;
    private  WeatherView weatherView;

    public WeatherPresenter(WeatherView weatherView) {
        this.weatherView = weatherView;
        this.weatherModel = new WeatherModel();
    }

    public void startApiCall(Context mContext){
        Observable.interval(1, TimeUnit.MINUTES, Schedulers.io())
                .subscribe(new Observer<Long>(){

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        apiCall(mContext);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        apiCall(mContext);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void apiCall(Context mContext){
        ApiModule.getInstance(mContext).getWeather(new ApiCallBack() {
            @Override
            public void OnApiCallBack(int mApiTag, int mResult, String mResponse) {
                Log.i("Callback", ""+mResponse+" "+mResponse);
                if(mResult == ApiModule.API_RESULT_SUCCESS) {
                    Weather(mContext);
                }else{
                    if(weatherView != null){
                        weatherView.showErrorMessage(mResponse);
                    }
                }
            }
        });
    }

    private void Weather(Context mContext){
        List<WeatherEntity> Weather = weatherModel.getHKWeather(mContext);
        Weather.addAll(weatherModel.getSGWeather(mContext));
        if(weatherView != null){
            weatherView.initAdapter(Weather);
        }
    }

    public void cancelApi(){
        if(mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
