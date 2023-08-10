package com.example.quoteappusingmvvm.util

import androidx.lifecycle.LiveData
import com.example.quoteappusingmvvm.model.Quote
import com.example.quoteappusingmvvm.model.QuoteDAO

class QuoteRepository(private var quoteDAO: QuoteDAO) {

    fun getQuote():LiveData<List<Quote>>{
        return quoteDAO.getQuote()
    }
    suspend fun insertQuote(quote: Quote){
        quoteDAO.insertQuote(quote)
    }

}