package com.example.quoteappusingmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quoteappusingmvvm.model.Quote
import com.example.quoteappusingmvvm.util.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository): ViewModel() {

    fun getQuotes():LiveData<List<Quote>>{
        return quoteRepository.getQuote()
    }
    fun insertQuote(quote:Quote){
        GlobalScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuote(quote)
        }
    }
}