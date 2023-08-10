package com.example.menudisplay

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var menuButton = findViewById<Button>(R.id.menubutton)
        registerForContextMenu(menuButton)                  //long press on button

        Log.d("MainActivity", "called")
        var menuButton2 = findViewById<Button>(R.id.menuButton2)
        menuButton2.setOnClickListener {
            Log.d("MainActivity", "Clicked")
            var popupMenu = PopupMenu(this, menuButton2)
            popupMenu.menuInflater.inflate(R.menu.popup_menu_list, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                Toast.makeText(this, it.title.toString(), Toast.LENGTH_LONG).show()
                true
            }
            popupMenu.show()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Context Menu")
        menu.add(0, v.id, 0, "Upload")
        menu.add(0, v.id, 0, "Search")
        menu.add(0, v.id, 0, "Share")
        menu.add(0, v.id, 0, "Bookmark")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "Selected Item: " + item.title, Toast.LENGTH_SHORT).show()
        return true
    }

    //method to inflate menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }
}

