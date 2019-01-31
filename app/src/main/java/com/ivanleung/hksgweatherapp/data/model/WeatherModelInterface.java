package com.ivanleung.hksgweatherapp.data.model;

import android.content.Context;

import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

public interface WeatherModelInterface {

    void InsertWeatherToDB(Context mContext, String response);

    List<WeatherEntity> getHKWeather(Context mContext);

    List<WeatherEntity> getSGWeather(Context mContext);

}
