package com.ivanleung.hksgweatherapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivanleung.hksgweatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CurrentViewHolde extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_temp)
        TextView tvTemp;
        @BindView(R.id.tv_humidity)
        TextView tvHumidity;

        public CurrentViewHolde(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_temp)
        TextView tvHistoryTemp;
        @BindView(R.id.tv_humidity)
        TextView tvHistoryHumidity;
        @BindView(R.id.tv_time)
        TextView tvHistoryTime;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
