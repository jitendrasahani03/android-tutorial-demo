package com.example.viewmodelexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var result: String
    lateinit var fab: FloatingActionButton
   // lateinit var binding: ActivityMainBinding
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
//        val view = binding.root
//        setContentView(view)
        val url: String = "https://type.fit/api/quotes"
        Log.d(TAG, "onCreate: starts")

        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        //running background thread....
        runBlocking {
            GlobalScope.async(Dispatchers.Default) {
                Log.d(TAG, "onCreate: Launching background thread...")
                result = downloadQuote(url)
                Log.d(TAG, "onCreate: Downloading of json quotes finished..")
                quoteViewModel.parseQuoteResult(result)

            }.await()
            GlobalScope.launch(Dispatchers.Main) {
                setCurrentIndex()
            }
        }
        dataBinding.btnNext.setOnClickListener {
            Log.d(TAG, "onClick: User clicked on next quote button starting...")
            nextQuoteIndex()
            setCurrentIndex()
        }
        dataBinding.btnPrevious.setOnClickListener {
            previousQuoteIndex()
            setCurrentIndex()
        }
        //dataBinding.
        dataBinding.mainactivity = this
    }


    private fun downloadQuote(url: String): String {
        Log.d(TAG, "downloadQuote: started...")
        Log.d(TAG, "downloadQuote urls is: $url")
        return URL(url).readText()
    }

    private fun updateView() {
        Log.d(TAG,"updateView() -> started")
        quoteViewModel.getLiveData().observe(this) {
            Log.d(TAG, "updateView() -> observer started")
            Log.d(TAG,"updateView() -> Observer Current Quote: $it")
            dataBinding.quote = it
            //dataBinding.cardview.texttitle.text = it.title
            //dataBinding.cardview.textauthor.text = it.author
            Log.d(TAG, "updateView() -> observer end")
        }
        Log.d(TAG,"updateView() -> ends")
    }
    private fun nextQuoteIndex() {
        quoteViewModel.getNextQuote()
    }

    private fun previousQuoteIndex() {
        quoteViewModel.getPrevQuote()
        setCurrentIndex()
    }

    private fun setCurrentIndex() {
        Log.d(TAG,"setCurrentIndex() : started")
        quoteViewModel.setLiveData()
        updateView()
        Log.d(TAG,"setCurrentIndex() : ends")
    }
    fun onFabClickEvent(){
        Log.d(TAG,"Fab : started")
        try {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,quoteViewModel.getLiveData().value.toString())
            startActivity(intent)
        }
        catch (e:Exception){
            Log.d(TAG,"Error message: ${e.printStackTrace().toString()}")
        }
        Log.d(TAG,"Fab : close")
    }
}