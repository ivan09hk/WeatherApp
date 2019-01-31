package com.ivanleung.hksgweatherapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ivanleung.hksgweatherapp.data.dao.WeatherDao;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

@Database(entities = {WeatherEntity.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public static final String DBNAME = "Weather";
    private static WeatherDatabase sInstance;

    public abstract WeatherDao weatherDao();

    public static WeatherDatabase getDatabase(Context context){
        if (sInstance == null){
            sInstance = Room
                    .databaseBuilder(context,
                            WeatherDatabase.class,
                            WeatherDatabase.DBNAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public static void destoryInstance(){
        if (sInstance != null)
            sInstance.close();
        sInstance = null;
    }

}
