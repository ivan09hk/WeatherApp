package com.ivanleung.hksgweatherapp.ui.weatherpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanleung.hksgweatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment implements WeatherView{

    @BindView(R.id.rv_hk)
    RecyclerView rvHK;
    @BindView(R.id.rv_sg)
    RecyclerView rvSG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initHKAdapter() {

    }

    @Override
    public void initSGAdapter() {

    }
}
