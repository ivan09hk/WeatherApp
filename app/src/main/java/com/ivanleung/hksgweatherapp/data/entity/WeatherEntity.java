package com.ivanleung.hksgweatherapp.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

import static com.ivanleung.hksgweatherapp.data.entity.WeatherEntity.TABLE_WEATHER;

@Entity(tableName = TABLE_WEATHER)
public class WeatherEntity {
    public static final String TABLE_WEATHER = "weather";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private long cityid;
    private String name;
    private String temp;
    private int humidity;
    private String dt;

    public WeatherEntity(long cityid, String name, String temp, int humidity, String dt) {
        this.cityid = cityid;
        this.name = name;
        this.temp = temp;
        this.humidity = humidity;
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCityid() {
        return cityid;
    }

    public void setCityid(long cityid) {
        this.cityid = cityid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
