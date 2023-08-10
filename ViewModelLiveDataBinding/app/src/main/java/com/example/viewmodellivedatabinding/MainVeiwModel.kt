package com.example.viewmodellivedatabinding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
private const val TAG = "MainViewModel"
class MainViewModel: ViewModel() {
val quotelivedata = MutableLiveData<String>("Message change")

    fun getQuoteText(){
        Log.d(TAG,"getQuoteText: called value ${quotelivedata.value}")
        quotelivedata.value = "Default message"
        Log.d(TAG,"getQuoteText: end value ${quotelivedata.value}")
    }
}