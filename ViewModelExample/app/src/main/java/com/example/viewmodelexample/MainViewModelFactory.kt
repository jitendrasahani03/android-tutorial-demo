package com.example.viewmodelexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val initialCount:Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(mainviewmodel: Class<T>): T {
        return MainViewModel(initialCount) as T
    }
}