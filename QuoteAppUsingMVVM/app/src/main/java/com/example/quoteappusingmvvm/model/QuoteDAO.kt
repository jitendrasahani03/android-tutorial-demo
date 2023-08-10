package com.example.quoteappusingmvvm.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quoteappusingmvvm.model.Quote

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM quote")
    fun getQuote() : LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote?)

    @Delete
    suspend fun deleteQuote(quote: Quote?)
}