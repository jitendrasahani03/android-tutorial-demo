package com.example.fragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnFragmentOne = findViewById<Button>(R.id.btn_fragment_one)
        val btnFragmentTwo = findViewById<Button>(R.id.btn_fragment_two)
        setFragmentToContainer(FragmentOne(),0)
        btnFragmentOne.setOnClickListener {
            setFragmentToContainer(FragmentOne(),1)
        }

        btnFragmentTwo.setOnClickListener {
            setFragmentToContainer(FragmentTwo(),1)
        }

    }
    private fun setFragmentToContainer(fragment:Fragment,flag:Int){

        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        if(flag==0){
            fragmentTransaction.add(R.id.frame_layout_container,fragment)
        }
        else
        {
            fragmentTransaction.replace(R.id.frame_layout_container,fragment)
        }
        fragmentTransaction.commit()
    }
}