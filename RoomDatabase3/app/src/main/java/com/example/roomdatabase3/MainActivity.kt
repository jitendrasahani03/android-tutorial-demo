package com.example.roomdatabase3

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabase3.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var personDatabase: PersonDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        personDatabase = PersonDatabase.getInstance(this)

        Log.d(TAG,"onCreate: started")

        binding.fab.setOnClickListener {
            Log.d(TAG,"Floating button: starts")
        //insertPerson(view)
            //fetchPersonRecords()
            //deletePerson()
            updatePerson()

            Log.d(TAG,"Floating button: end")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun fetchPersonRecords(){
        Log.d(TAG,"fetchPersonRecords: starts")
        personDatabase.getPersonRecords().getAllPerson().observe(this,{it->
              Log.d(TAG,"count of person ${it.size}")
            for (p in 0 until it.size){
                Log.d(TAG,"first_name: ${it[p].first_name}")
            }

        })
        Log.d(TAG,"fetchPersonRecords: end")
    }

    private fun insertPerson(view: View){
        GlobalScope.launch {
            val i = personDatabase.getPersonRecords().insertPerson(Person(0,"Jitendra","Sahani","abcd@gmail.com"))
            Log.d(TAG,"Value return by insertQuery: $i")
            personDatabase.getPersonRecords().insertPerson(Person(0,"Anil","Sahani","anil@gmail.com"))
            Snackbar.make(view, "Data Insert...", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
    }
    private fun deletePerson(){
        GlobalScope.launch {
            Log.d(TAG,"deletePerson: started")
            val rawCount = personDatabase.getPersonRecords().deletePerson(Person(1))
            Log.d(TAG,"deletePerson row count : $rawCount")
            Log.d(TAG,"deletePerson: end")
        }


    }

    private fun updatePerson(){
        GlobalScope.launch {
            Log.d(TAG,"updatePerson: started")
            val rawCount = personDatabase.getPersonRecords().updatePerson(Person(3,"Anil2"))
            Log.d(TAG,"updatePerson row count : $rawCount")
            Log.d(TAG,"updatePerson: end")
        }
    }

}