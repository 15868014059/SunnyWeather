package com.unnyweather.android.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//Retrofit构建器
public  class ServiceCreator {
    // 用BASE_URL指定Retrofit的根路径
    private static final String BASE_URL = "https://api.caiyunapp.com/";
    //内部使用Retrofit.Builder()构建一个Retrofit
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //外部可见create（）方法，接收Class类型参数
    static public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
