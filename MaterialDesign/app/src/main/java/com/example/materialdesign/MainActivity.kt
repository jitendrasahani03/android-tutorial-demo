package com.example.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.subtitle = "SubTitle"
        toolbar.setSubtitleTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        //toolbar.title = "Home PAge"
        toolbar.setTitleTextColor(ContextCompat.getColor(applicationContext,R.color.white))
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
       // toolbar.inflateMenu(R.menu.menu)
        val name = ArrayList<String>()
        name.add("Akash")
        name.add("Mukesh")

        val listView = findViewById<ListView>(R.id.listview)
        val arrayAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,name)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, name[position],Toast.LENGTH_SHORT).show()
        }

       registerForContextMenu(listView)


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        menu?.setHeaderTitle("Select One Option")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext,item.title,Toast.LENGTH_SHORT).show()
        return true
    }
//option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}