package com.example.roomdatabase3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personTb")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "first_name")
    val first_name: String? = null,     //you can write @ColumnInto(name = "Column_name" if name is different from variable name
    @ColumnInfo(name = "last_name")
    val last_name: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null
)
