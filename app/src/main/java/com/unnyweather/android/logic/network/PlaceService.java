package com.unnyweather.android.logic.network;

import com.unnyweather.android.SunnyWeatherApplication;
import com.unnyweather.android.logic.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//定义访问彩云天气城市搜索API的Retrofit接口
public interface PlaceService {
    // @GET可以在调用searchPlaces（）方法，Retrofit自动发起一条GET请求
    @GET("v2/place?token="+ SunnyWeatherApplication.TOKEN+"&lang=zh_CN")
    //    searchPlaces（）方法的返回值声明成 Call<PlaceResponse>，Retrofit会将返回的JSON数据解析成PlaceResponse对象
    Call<PlaceResponse> searchPlaces(@Query("query") String query);
}
