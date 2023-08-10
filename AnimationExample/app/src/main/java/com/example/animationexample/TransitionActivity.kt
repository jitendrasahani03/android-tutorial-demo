package com.example.animationexample

import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionInflater
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener

class TransitionActivity : AppCompatActivity() {
    private val TAG = "TransitionActivity"
    private var type: Constants.TransitionType? = null
    private lateinit var toolbarTitle: String
    private lateinit var circularRevealLayout: RelativeLayout

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: started")
        setContentView(R.layout.activity_transition)
        circularRevealLayout = findViewById(R.id.shared_layout)

        circularRevealLayout.setOnClickListener {
            displayCircularAnimation(circularRevealLayout)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            initPage()
        }
        Log.d(TAG, "onCreate: ends")
        initiateTransition()
        window.allowEnterTransitionOverlap = false
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun displayCircularAnimation(view: View) {
        Log.d(TAG, "displayCircularAnimation: started")
        val foregroundTextView = findViewById<TextView>(R.id.circular_text_view)
        val centerX = (view.left + view.right) / 2
        val centerY = (view.top + view.bottom) / 2

        val radius = (centerX.coerceAtLeast(centerY)) / 2.0f
//check if text view is visible to not
        if (foregroundTextView.visibility == View.INVISIBLE) {
            foregroundTextView.visibility = View.VISIBLE
            foregroundTextView.text = "Hi"
            //animation code
            ViewAnimationUtils.createCircularReveal(
                foregroundTextView,
                centerX,
                centerY,
                0.0f,
                radius
            ).start()
        } else {
            val reveal = ViewAnimationUtils.createCircularReveal(
                foregroundTextView,
                centerX,
                centerY,
                radius,
                0.0f
            )
            reveal.addListener(onEnd = {
                foregroundTextView.visibility = View.INVISIBLE
            })
            reveal.start()
        }
        Log.d(TAG, "displayCircularAnimation: ends")

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSupportNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }

    private fun initPage() {
        Log.d(TAG, "initPage: started")
        try {
            type = intent.getSerializableExtra(animationType) as Constants.TransitionType
            //Log.d(TAG, "initPage ${Constants.TransitionType.values().get(type.ordinal)}")
            val tool = intent.getSerializableExtra(animationTitle) as Constants.TransitionTitle
            Log.d(TAG, "${tool.name.toString()}")

            toolbarTitle = tool.name
            supportActionBar?.title = toolbarTitle
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d(TAG, "initPage: ends")
    }

    override fun onBackPressed() {
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initiateTransition() {
        Log.d(TAG, "initiateTransition: started")
        when (type) {
            Constants.TransitionType.EXPLODE_KOTLIN -> {
                val explode = Explode()  //android.transition
                explode.duration = 1000
                window.enterTransition = explode
            }
            Constants.TransitionType.EXPLODE_XML -> {
                val transition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
                window.enterTransition = transition
            }
            Constants.TransitionType.FADE_KOTLIN -> {
                val fade = Fade()
                fade.duration = 1000
                window.enterTransition = fade

            }
            Constants.TransitionType.FADE_XML -> {
                val transition = TransitionInflater.from(this).inflateTransition(R.transition.fade)
                window.enterTransition = transition
            }
            Constants.TransitionType.SLIDE_KOTLIN -> {
                val slide = Slide()
                slide.duration = 1000
                slide.slideEdge = Gravity.LEFT
                slide.interpolator = AccelerateInterpolator()
                window.enterTransition = slide
            }
            Constants.TransitionType.SLIDE_XML -> {
                val transition = TransitionInflater.from(this).inflateTransition(R.transition.slide)
                window.enterTransition = transition
            }

            else -> {}
        }
        Log.d(TAG, "initiateTransition: ends")
    }
}