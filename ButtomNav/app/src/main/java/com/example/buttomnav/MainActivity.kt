package com.example.buttomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private val ROOT_FRAGMENT_TAG = "root_element"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instance of bottomnavigationlayout
        var bottonNavigation = findViewById<BottomNavigationView>(R.id.bottomnavigationBar)
        showFragment(PlayFragment(), 0)
        //set listener
        var navigationListener = NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.play_button -> {
                    showFragment(PlayFragment.getInstance("Jitendra",20), 0)
                }

                R.id.pause_button -> {
                    showFragment(PauseFragment(), 1)
                }

                R.id.stop_button -> {
                    showFragment(StopFragment(), 1)
                }

                R.id.download_button -> {
                    showFragment(DownloadFragment(), 1)
                }
            }
            true
        }

        bottonNavigation.setOnItemSelectedListener(navigationListener)
        // bottonNavigation.selectedItemId=  R.id.play_button
    }

    private fun showFragment(fragment: Fragment, flag: Int) {
        var fm: FragmentManager = supportFragmentManager
        var ft = fm.beginTransaction()

        if (flag==0) {
            ft.add(R.id.container, fragment)
            fm.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE)
            ft.addToBackStack(ROOT_FRAGMENT_TAG)
        } else {
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(null)
        }
        ft.commit()

    }
    fun callFromFragment(){
        Log.d("MainActivity","callFromFragment:called")
    }
}