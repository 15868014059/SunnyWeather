package com.unnyweather.android.logic.dao;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.unnyweather.android.SunnyWeatherApplication;
import com.unnyweather.android.logic.model.Place;
//封装了存储和读取数据的接口
public class PlaceDao  {
//把Place对象存储到sharedPreferences文件中
    public static void savePlace(Place place)
    {
        //通过GSON将Place对象转成一个JSON字符串
        sharedPreferences().edit().putString("place",new Gson().toJson(place));

    }


//读取
    public static Place getSavedPlace(){
        //通过GSON将JSON字符串解析成Place对象并返回
        String place = sharedPreferences().getString("place", "");
        return  new Gson().fromJson(place,Place.class);
    }
//判断是否有数据以被存储
    public static boolean isPlacesSaved(){
        return  sharedPreferences().contains("place");
    }

    private static SharedPreferences sharedPreferences(){
        return   SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE);
    }


}

