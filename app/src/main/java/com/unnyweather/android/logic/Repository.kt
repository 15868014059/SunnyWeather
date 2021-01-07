package com.unnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import com.unnyweather.android.logic.Repository.getSavedPlace
import com.unnyweather.android.logic.Repository.savePlace
import com.unnyweather.android.logic.dao.PlaceDao
import com.unnyweather.android.logic.model.Place
import com.unnyweather.android.logic.model.Weather


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext
//仓库层
object Repository {
//searchPlaces()函数用来搜索城市数据
    //LiveData()函数自动构建返回一个LiveData对象在它的代码块中提供一个挂起函数的上下文 ，可以调用任意的挂起函数
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            //Result.success（）包装获取到的城市数据信息
            Result.success(places)
        } else {
            //包装异常信息
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    //
    fun refreshWeather(lng: String, lat: String, placeName: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse, dailyResponse)
                Result.success(weather)
            } else {
                Result.failure(
                        RuntimeException(
                                "realtime response status is ${realtimeResponse.status}" +
                                        "daily response status is ${dailyResponse.status}"
                        )
                )
            }
        }
    }


    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            //因为无法直接取得返回的LiveData对象所以用Emit()将包装的结果发射出去
            emit(result)
        }
    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace()= PlaceDao.getSavedPlace()

    fun isPlaceSaved()= PlaceDao.isPlacesSaved()
}