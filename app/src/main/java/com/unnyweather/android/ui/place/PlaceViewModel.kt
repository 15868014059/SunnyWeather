package com.unnyweather.android.ui.place

import androidx.lifecycle.*

import com.unnyweather.android.logic.Repository
import com.unnyweather.android.logic.model.Place
//逻辑层
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()
//当searchPlaces()函数被调用时，switcMap()方法所对应的转换函数就会执行。
// 调用仓库层中定义的searchPlaces()方法 就可以发起网络请求，同时将仓库层返回的LiveData对象转换成一个可 供Activity 观察的LiveData对象。
//PlaceViewModel中定义了一个placeList集合，用于对界面上显示的城市数据进行缓存，
// 原则上与界面相关的数据都应该放到ViewModel中，保证它们在手机屏幕发生旋转的时候不会丢失，
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }



}