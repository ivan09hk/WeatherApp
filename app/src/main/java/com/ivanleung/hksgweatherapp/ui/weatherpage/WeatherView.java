package com.ivanleung.hksgweatherapp.ui.weatherpage;

import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.List;

public interface WeatherView {

    void initAdapter(List<WeatherEntity> Weather);

    void showErrorMessage(String errorMessage);

}
