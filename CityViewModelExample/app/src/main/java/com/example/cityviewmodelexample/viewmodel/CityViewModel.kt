package com.example.cityviewmodelexample.viewmodel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityviewmodelexample.model.City
import com.example.cityviewmodelexample.model.CityDataProvider
import java.util.logging.Handler
private const val TAG = "CityViewModel"
class CityViewModel: ViewModel() {
    private val liveData = MutableLiveData<City>()          //to change modify when require
    private val cities = CityDataProvider().getCities()     //getting data of all city from city provider class
    private var currentIndex = 0
    private val delay = 2000L

    init {
        Log.d(TAG,"CityViewModel init block: starts")
        loop()
        Log.d(TAG,"CityViewModel init block: ends")
 }
    private fun loop(){
        Log.d(TAG,"CityViewModel loop(): starts")
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            updateCity()
        },delay)
        Log.d(TAG,"CityViewModel loop(): ends")

    }
    private fun updateCity(){
            currentIndex++
            currentIndex %= cities.size
            liveData.value = cities[currentIndex]
            loop()
    }
    public fun getLiveData():LiveData<City> = liveData

}