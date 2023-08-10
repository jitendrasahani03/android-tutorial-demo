package com.example.quoteappusingmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var author: String
)