package com.example.roomdatabase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var contactDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactDatabase = ContactDatabase.getInstance(this)
      /*  contactDatabase = Room.databaseBuilder(applicationContext,ContactDatabase::class.java,"contactDb")
            .build()*/
        GlobalScope.launch {
            contactDatabase.getContact().insertContact(Contact(0,"jitendra","1234567890"))
            contactDatabase.getContact().insertContact(Contact(0,"Rohan","1234567891"))
            contactDatabase.getContact().insertContact(Contact(0,"Mohan","1234567892"))
       }
       // val contactList = contactDatabase.getContact().getAllContact()
    }
}