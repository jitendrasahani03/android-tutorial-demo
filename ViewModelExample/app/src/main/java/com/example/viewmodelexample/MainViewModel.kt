package com.example.viewmodelexample

import androidx.lifecycle.ViewModel

class MainViewModel(private val initialCount: Int): ViewModel() {
    private var count = initialCount
    fun increment(){
        count++
    }
    fun getCount() = count
}