package com.example.databaseexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myDatabaseHelper = MyDatabaseHelper(this)
        myDatabaseHelper.addContact("Jitendra", "9800196909")
        myDatabaseHelper.addContact("Jitendra", "9800196909")
        myDatabaseHelper.addContact("Jitendra", "9800198909")
        myDatabaseHelper.addContact("Dharambir", "9932414069")
        myDatabaseHelper.addContact("Anil", "123456789")

        myDatabaseHelper.updateContact()

        val cursor = myDatabaseHelper.fetchAllContacts()
        while (cursor.moveToNext()) {
            val contact = Contact(
                cursor.getInt(0), cursor.getString(1),
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PHONE_NO))
            )
            Log.d(TAG, "Data from table $contact")
        }
        //myDatabaseHelper.deleteContact()
    }
}