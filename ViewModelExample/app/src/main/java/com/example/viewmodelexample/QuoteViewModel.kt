package com.example.viewmodelexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
private const val TAG = "QuoteViewModel"
class QuoteViewModel: ViewModel() {
    private var index = 0

    //to hold the arrayList of all quotes
    private val quotes = ArrayList<Quote>()

    private val quoteMutableLiveData = MutableLiveData<Quote>()

    fun getNextQuote()
    {
        index++
        index %= quotes.size
    }
    fun getPrevQuote()
        {
            index--
            if (index<0){
                index = 0
            }
        }
    fun parseQuoteResult(result: String) {
        Log.d(TAG,"parseQuoteResult: fetching json title and author started....")
        val json = JSONArray(result)
        Log.d(TAG,"Total quotes present int json: ${json.length()}")

        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val title = json.getJSONObject(i).getString("text")
            val author = json.getJSONObject(i).getString("author")
            if(!json.getJSONObject(i).isNull("author")){
                if (quotes.contains(Quote(title, author))){}
                else{quotes.add(Quote(title, author))}
            }
     }
        Log.d(TAG,"parseQuoteResult: Total quotes added to quote array list: ${quotes.size}")
    }
    fun setLiveData(){
        Log.d(TAG,"setLiveData(): started")
        quoteMutableLiveData.value = quotes[index]
        Log.d(TAG,"setLiveData(): value added to mutableLiveData is ${quoteMutableLiveData.value}")
        Log.d(TAG,"setLiveData(): ends")
    }

    fun getLiveData():LiveData<Quote> {
        Log.d(TAG,"getLiveData(): started..")
        Log.d(TAG,"getLiveData(): value in mutablelive data is ${quoteMutableLiveData.value}")
        Log.d(TAG,"getLiveData(): ends")
        return quoteMutableLiveData
    }
}