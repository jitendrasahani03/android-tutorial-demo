package com.example.roomdatabase2

import androidx.room.Entity
import androidx.room.PrimaryKey

//model class schema of table

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val phone: String?
)
