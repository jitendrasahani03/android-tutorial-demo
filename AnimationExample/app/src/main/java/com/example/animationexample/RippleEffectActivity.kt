package com.example.animationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RippleEffectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_effect_ripple)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}