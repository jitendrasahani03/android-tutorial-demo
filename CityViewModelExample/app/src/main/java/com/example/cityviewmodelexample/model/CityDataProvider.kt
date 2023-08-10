package com.example.cityviewmodelexample.model

import android.util.Log
import com.example.cityviewmodelexample.R
private const val TAG = "CityDataProvider"
class CityDataProvider {
    private val cities = arrayListOf<City>()

    init {
        cities.add(City("China",R.drawable.china,10_100_100))
        cities.add(City("Japan",R.drawable.japan,10_100_200))
        cities.add(City("New York",R.drawable.newyork,10_100_300))
    }
    fun getCities():ArrayList<City>{
        Log.d(TAG,"getCities: called")
        return cities
    }
}