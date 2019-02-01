package com.ivanleung.hksgweatherapp.ui.weatherpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivanleung.hksgweatherapp.R;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment implements WeatherView{

    @BindView(R.id.rv_weather)
    RecyclerView rvWeather;

    WeatherPresenter presenter;

    WeatherAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new WeatherPresenter(this);
        presenter.startApiCall(getContext());
    }

    @Override
    public void initAdapter(List<WeatherEntity> Weather) {
        if(adapter == null) {
            adapter = new WeatherAdapter(getContext(), Weather);
            rvWeather.setAdapter(adapter);
            rvWeather.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            Log.i("APi", "Update");
            adapter.updateAdapter(Weather);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        presenter.cancelApi();
        super.onDestroyView();
    }
}
