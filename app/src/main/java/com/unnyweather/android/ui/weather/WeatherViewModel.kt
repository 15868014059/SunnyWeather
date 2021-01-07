package com.unnyweather.android.ui.weather



import androidx.lifecycle.*
import com.unnyweather.android.logic.Repository
import com.unnyweather.android.logic.model.Location

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()
//下面定义的三个变量放到ViewModel中可以保证屏幕发生旋转时数据不会丢失
    var locationLng = ""

    var locationLat = ""

    var placeName = ""


//Transformations.switchMap（）观察Location对象，并在sitchMap()方法的 转换丽数中调用仓库层中定义的refreshWeather（）方法
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat, placeName)
    }
//refreshWeather（）方法刷新新天气，并将传入的经纬度封装成一个Location对象后赋值给locationLiveData
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}