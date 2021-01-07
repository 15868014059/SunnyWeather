package com.sunnyweather.android.logic.network

import com.unnyweather.android.logic.network.PlaceService
import com.unnyweather.android.logic.network.ServiceCreator
import com.unnyweather.android.logic.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

//SunnyWeatherNetwork接收一个Lambda表达式参数，把当前的协程立即挂起
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getDailyWeather(lng: String,lat: String) = weatherService.getDailyWeather(lng,lat).await()
    suspend fun getRealtimeWeather(lng: String,lat: String) = weatherService.getRealtimeWeather(lng,lat).await()
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
//await()一个挂起函数   await()函数中使用了suspendCoroutine函数来挂起当前协程
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}