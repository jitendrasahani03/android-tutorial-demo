package com.example.tablayoutapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerMessengerAdapter(fm: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {
    //no of tabs

    override fun getItemCount(): Int {
        return 2
    }

    //calling fragment based on position
    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return CallFragment()
        }
        return return MessageFragment()
    }
}