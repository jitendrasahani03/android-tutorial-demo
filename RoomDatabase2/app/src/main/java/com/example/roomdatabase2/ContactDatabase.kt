package com.example.roomdatabase2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase:RoomDatabase() {
    abstract fun getContact():ContactDao

    companion object {
        private var instance: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase{
                    if (instance==null)
                    {
                        synchronized(this){
                            if (instance==null){
                                instance = Room.databaseBuilder(context.applicationContext,ContactDatabase::class.java,"contact1").build()
                            }
                        }
                    }
                    return instance!!
        }
    }
}