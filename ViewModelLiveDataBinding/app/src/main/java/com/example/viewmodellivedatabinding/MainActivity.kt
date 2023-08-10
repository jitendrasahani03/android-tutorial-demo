package com.example.viewmodellivedatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodellivedatabinding.databinding.ActivityMainBinding
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.d(TAG,"onCreate: called")

        binding.mainviewmodel = mainViewModel
        Log.d(TAG,"onCreate: main view model value ${mainViewModel.quotelivedata.value}")

       binding.lifecycleOwner = this

        Log.d(TAG,"onCreate: end")


    }
}