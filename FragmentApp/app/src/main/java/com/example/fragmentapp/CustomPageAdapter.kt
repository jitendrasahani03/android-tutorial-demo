package com.example.fragmentapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
enum class LayoutModule {
    PHRASE{
        override fun getResourceID(): Int {
            return R.layout.fragment_one
        }
    },
    NUMBER{
        override fun getResourceID(): Int {
            return R.layout.fragment_one
        }
    },
    FAMILY{
        override fun getResourceID(): Int {
            return R.layout.fragment_one
        }
    },COLOR{
        override fun getResourceID(): Int {
            return R.layout.fragment_one
        }
    };

    abstract fun getResourceID() : Int
}

class CustomPageAdapter (context: Context,name:ArrayList<Int>): PagerAdapter() {
    private final var mContext:Context
    private val mName : ArrayList<Int>
    init {
        mContext = context
        mName = name
    }
    override fun getCount(): Int {
        return  LayoutModule.values().size
    }
//similar to getView of Adapter

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val layoutModule : Int = LayoutModule.values()[position].getResourceID()
        var view = LayoutInflater.from(mContext).inflate(layoutModule,container,false)
        (container as ViewPager).addView(view)
        return view
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}