package com.example.listviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name = arrayOf("A","B","C")
        var arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name)
        var listView = findViewById<ListView>(R.id.list)
            listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,name[parent.getItemIdAtPosition(position).toInt()],Toast.LENGTH_LONG).show()
        }

    }
}