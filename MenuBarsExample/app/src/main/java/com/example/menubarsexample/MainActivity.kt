package com.example.menubarsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var buttonPopUp: Button
    private var actionMode: ActionMode? = null
    private lateinit var parentLayout: CoordinatorLayout
    private lateinit var onClickListener: View.OnClickListener
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        parentLayout = findViewById(R.id.parent_layout)
        buttonPopUp = findViewById(R.id.button_one)
        onClickListener = View.OnClickListener { 
            Toast.makeText(this,"Item Deleted",Toast.LENGTH_SHORT).show()
        }
        
        //on click of button display the popup menu
        buttonPopUp.setOnClickListener {
            Log.d(TAG, "Button Clicked")
            val popupMenu = PopupMenu(this, buttonPopUp)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                Log.d(TAG, "setOnMenuItemClickListener")
                Toast.makeText(this, it.title.toString(), Toast.LENGTH_LONG).show()
                true
            }
            popupMenu.show()
        }
        val name = ArrayList<String>()
        name.add("Akash")
        name.add("Mukesh")

        val listView = findViewById<ListView>(R.id.listview)
        registerForContextMenu(listView)// refer to id of listview or gridview

        val arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, name)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, name[position], Toast.LENGTH_SHORT).show()
        }

        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item: MenuItem? ->
            when(item?.itemId){
                R.id.camera -> { actionMode = startActionMode(CallBackActionMode())!!
                    true}
                else -> false
            }
        })
    }
    class CallBackActionMode: ActionMode.Callback{
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextual_menu,menu)
            return true

        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.title = "Action Mode"
            mode?.subtitle = "Action SubTitle"
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {


            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {

        }

    }

    //method to show option menu in appbar section
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_group -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            R.id.new_broadcast -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            R.id.linked_devices -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            R.id.starred_messages -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            R.id.payments -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            R.id.settings -> {
                showOptionMenuMessage(item.title.toString())
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showOptionMenuMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }


    //Floating context menu methods
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.floating_context_menu,menu)
        menu?.setHeaderTitle("Choose One")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        //Toast.makeText(this, "Selected Item: " + item.title, Toast.LENGTH_SHORT).show()
        return when(item.itemId){
            R.id.call -> {showSimpleSnackBar(item.title.toString())
                true
            }

            R.id.sms -> {showSnackbarWithAction(item.title.toString())
                true
            }

            else -> false
        }
    }
    //attaching to parent view with simple message
    private fun showSimpleSnackBar(message:String){
        Snackbar.make(parentLayout,message,LENGTH_SHORT)
            .setAnimationMode(ANIMATION_MODE_FADE)
            .show()
    }
    private fun showSnackbarWithAction(message:String){
        Snackbar.make(parentLayout,message,LENGTH_SHORT)
            .setAction("DELETE",onClickListener)
            .setActionTextColor(ContextCompat.getColor(applicationContext,R.color.white))
            .setBackgroundTint(ContextCompat.getColor(applicationContext,R.color.oceanBlue))
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .show()
    }
}