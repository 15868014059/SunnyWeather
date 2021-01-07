package com.unnyweather.android.ui.place;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.unnyweather.android.logic.model.Weather;
import com.unnyweather.android.ui.weather.WeatherActivity;
import com.unnyweather.android.R;
import com.unnyweather.android.logic.model.Place;

import java.util.List;
//适配器
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    PlaceFragment fragment;
    List<Place> placeList;

    public PlaceAdapter(PlaceFragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    //给place_ item.xml 的最外层布局注册了一个点击事件监听器，然后在点击事件中获取当前点击项的经纬度坐标和地区名称，并把它们传人Intent中
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            Place place = placeList.get(adapterPosition);

            FragmentActivity activity = fragment.getActivity();
            if(activity instanceof WeatherActivity){
                WeatherActivity weatherActivity=(WeatherActivity)activity;

                weatherActivity.getViewModel().setLocationLng(place.getLocation().getLng());
                weatherActivity.getViewModel().setLocationLat(place.getLocation().getLat());
                weatherActivity.getViewModel().setPlaceName(place.getName());
                weatherActivity.refreshWeather();
                weatherActivity.close();
            }else{
                Intent intent = new Intent(parent.getContext(), WeatherActivity.class);
                intent.putExtra("location_lng",place.getLocation().getLng());
                intent.putExtra("location_lat",place.getLocation().getLat());
                intent.putExtra("place_name",place.getName());
//调用Fragment的startActivity( )方法启动WeatherActivity。
                fragment.startActivity(intent);

            }
            fragment.getViewModel().savePlace(place);
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView placeName = itemView.findViewById(R.id.placeName);
        TextView placeAddress = itemView.findViewById(R.id.placeAddress);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}