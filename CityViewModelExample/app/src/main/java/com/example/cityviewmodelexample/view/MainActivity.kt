package com.example.cityviewmodelexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.cityviewmodelexample.R
import com.example.cityviewmodelexample.databinding.ActivityMainBinding
import com.example.cityviewmodelexample.viewmodel.CityViewModel
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val cityViewModel: CityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"onCreate: starts")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG,"getLiveData ${cityViewModel.getLiveData().value?.name})")
        cityViewModel.getLiveData().observe(this) { city ->
            Log.d(TAG,"")
            binding.imageView.setImageResource(city.image)
            binding.cityLocation.text = city.name
            binding.cityPopulation.text = city.population.toString()

        }
        Log.d(TAG,"onResume: ends")
    }
}