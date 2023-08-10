package com.example.tablayoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //instantiate viewpage and tab
        var viewpager = findViewById<ViewPager2>(R.id.viewpager)
        var tabs = findViewById<TabLayout>(R.id.tabs)

        //to set viewpage and fragment
        val adapter = ViewPagerMessengerAdapter(supportFragmentManager,lifecycle)
        viewpager.adapter = adapter

        //to setup tab title TabLayoutMediator
        TabLayoutMediator(tabs,viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Call"
                1 -> tab.text = "Message"
            }
        }.attach()

    }
}