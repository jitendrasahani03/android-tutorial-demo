package com.example.appbarapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to setup Custom Action Bar
        var toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //to customise you can call getSupportActionBar
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setTitle("My App")
        toolbar.setSubtitle("Welcome")
        toolbar.setSubtitleTextColor(ContextCompat.getColor(applicationContext,R.color.white))
    }
    //to inflate option menu items

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //onItemClick event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.new_item -> Toast.makeText(applicationContext,"New",Toast.LENGTH_SHORT).show()
            R.id.copy_item -> Toast.makeText(applicationContext,"New",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}