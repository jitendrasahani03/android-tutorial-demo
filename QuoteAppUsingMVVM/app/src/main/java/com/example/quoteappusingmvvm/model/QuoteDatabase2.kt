package com.example.quoteappusingmvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase2 : RoomDatabase() {
    abstract  fun getQuoteDao(): QuoteDAO
    companion object {
        private var instance: QuoteDatabase2? = null
        fun getInstance(context: Context): QuoteDatabase2 {
            if (instance == null) {
                synchronized(this) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            QuoteDatabase2::class.java,
                            "quoteDb"
                        ).createFromAsset("quotes.db")
                            .build()
                    }
                }
            return instance!!
            }

        }
    }