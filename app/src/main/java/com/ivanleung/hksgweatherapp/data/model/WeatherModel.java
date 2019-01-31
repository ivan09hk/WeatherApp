package com.ivanleung.hksgweatherapp.data.model;

import android.content.Context;
import android.util.Log;

import com.ivanleung.hksgweatherapp.data.WeatherDatabase;
import com.ivanleung.hksgweatherapp.data.dao.WeatherDao;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherModel implements WeatherModelInterface{


    @Override
    public void InsertWeatherToDB(Context mContext, String response) {
        WeatherDao weatherDao = WeatherDatabase.getDatabase(mContext).weatherDao();
        Log.i("API", "Response "+response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject weather = jsonArray.getJSONObject(i);
                JSONObject main = weather.getJSONObject("main");
                WeatherEntity entity = new WeatherEntity(
                        weather.getLong("id"),
                        weather.getString("name"),
                        main.getString("temp"),
                        main.getInt("humidity"),
                        String.valueOf(System.currentTimeMillis())
                );
                weatherDao.insert(entity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WeatherEntity> getHKWeather(Context mContext) {
        WeatherDao weatherDao = WeatherDatabase.getDatabase(mContext).weatherDao();
        return weatherDao.getHKWeather();
    }

    @Override
    public List<WeatherEntity> getSGWeather(Context mContext) {
        WeatherDao weatherDao = WeatherDatabase.getDatabase(mContext).weatherDao();
        return weatherDao.getSGWeather();
    }
}
