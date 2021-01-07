package com.unnyweather.android.logic.network;

import com.unnyweather.android.SunnyWeatherApplication;
import com.unnyweather.android.logic.model.DailyResponse;
import com.unnyweather.android.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//@GET注解来声明要访问的API接口
//@Path注解来向请求接口中动态传入经纬度的坐标

public interface WeatherService {
    //getRealtimeWeather（）用于获取实时的天气信息
    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather(@Path("lng") String lng, @Path("lat") String lat);
//getDailyWeather（）用于获取未来的天气信息
    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather(@Path("lng") String lng, @Path("lat") String lat);
}
//两个方法的返回值分别被声明成了Call<RealtimeResponse>和Call<DailyResponse>对应DailyResponse中的两个数据模型类