package com.example.roomdatabase3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun getPersonRecords():PersonDao
    //singleton object of PersonDatabase

    companion object{
        private var INSTANCE: PersonDatabase? = null
        fun getInstance(context: Context): PersonDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context,PersonDatabase:: class.java, "personDb")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}