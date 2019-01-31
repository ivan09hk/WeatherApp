package com.ivanleung.hksgweatherapp.data.model;

import android.content.Context;

import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.ArrayList;

public interface WeatherModelInterface {

    void InsertWeatherToDB(Context mContext, String response);

    ArrayList<WeatherEntity> getHKWeather();

    ArrayList<WeatherEntity> getSGWeather();

}
