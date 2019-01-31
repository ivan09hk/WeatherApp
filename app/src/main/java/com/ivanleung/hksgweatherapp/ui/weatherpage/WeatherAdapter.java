package com.ivanleung.hksgweatherapp.ui.weatherpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivanleung.hksgweatherapp.R;
import com.ivanleung.hksgweatherapp.api.ApiModule;
import com.ivanleung.hksgweatherapp.data.entity.WeatherEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context mContext;
    List<WeatherEntity> listOfWeather;
    boolean isFirstSG = true;

    public WeatherAdapter(Context mContext, List<WeatherEntity> listOfWeather) {
        this.mContext = mContext;
        this.listOfWeather = listOfWeather;
        this.isFirstSG = true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 0){
            View currentView = LayoutInflater.from(mContext).inflate(R.layout.item_current, viewGroup, false);
            CurrentViewHolder viewHolde = new CurrentViewHolder(currentView);
            return viewHolde;
        }else{
            View historyView = LayoutInflater.from(mContext).inflate(R.layout.item_history, viewGroup, false);
            HistoryViewHolder viewHolde = new HistoryViewHolder(historyView);
            return viewHolde;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        WeatherEntity weather = listOfWeather.get(i);
        if(viewHolder.getItemViewType() == 0){
            if(weather.getCityid() == Long.parseLong(ApiModule.SGID) && isFirstSG){
                isFirstSG = false;
            }
            CurrentViewHolder holder = (CurrentViewHolder) viewHolder;
            holder.tvName.setText(weather.getName());
            holder.tvTemp.setText(weather.getTemp()+" F");
            holder.tvHumidity.setText(mContext.getString(R.string.humidity_text, String.valueOf(weather.getHumidity())));
        }else{
            HistoryViewHolder holder = (HistoryViewHolder) viewHolder;
            holder.tvHistoryTemp.setText(weather.getTemp()+" F");
            holder.tvHistoryHumidity.setText(mContext.getString(R.string.humidity_text, String.valueOf(weather.getHumidity())));
        }
    }

    @Override
    public int getItemViewType(int position) {
        WeatherEntity weather = listOfWeather.get(position);
        WeatherEntity pWeather = listOfWeather.get(position > 0 ? position-1 : 0);
        if(position == 0 || (weather.getCityid() != pWeather.getCityid())){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return listOfWeather.size();
    }

    public void updateAdapter(List<WeatherEntity> listOfWeather){
        this.listOfWeather = listOfWeather;
        notifyDataSetChanged();
    }

    class CurrentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_temp)
        TextView tvTemp;
        @BindView(R.id.tv_humidity)
        TextView tvHumidity;

        public CurrentViewHolder(@NonNull View itemView) {
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
