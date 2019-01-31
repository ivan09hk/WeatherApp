package com.ivanleung.hksgweatherapp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ivanleung.hksgweatherapp.api.ApiModule;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM "+ WeatherEntity.TABLE_WEATHER+" WHERE cityid="+ ApiModule.HKID+" ORDER BY dt DESC LIMIT 6")
    public List<WeatherEntity> getHKWeather();

    @Query("SELECT * FROM "+ WeatherEntity.TABLE_WEATHER+" WHERE cityid="+ ApiModule.SGID+" ORDER BY dt DESC LIMIT 6")
    public List<WeatherEntity> getSGWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(WeatherEntity weatherEntity);

}
