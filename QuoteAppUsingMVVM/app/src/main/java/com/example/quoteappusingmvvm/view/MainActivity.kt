package com.example.quoteappusingmvvm.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quoteappusingmvvm.viewmodel.MainViewModel
import com.example.quoteappusingmvvm.viewmodel.MainViewModelFactory
import com.example.quoteappusingmvvm.model.QuoteDAO
import com.example.quoteappusingmvvm.model.QuoteDatabase2
import com.example.quoteappusingmvvm.util.QuoteRepository
import com.example.quoteappusingmvvm.R
import com.example.quoteappusingmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var quoteDao: QuoteDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        quoteDao = QuoteDatabase2.getInstance(this).getQuoteDao()
        val quoteRepository = QuoteRepository(quoteDao)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(quoteRepository)).get(
            MainViewModel::class.java)
        mainViewModel.getQuotes().observe(this,{
           binding.txtQuote.text = it.toString()
        })
    }
}