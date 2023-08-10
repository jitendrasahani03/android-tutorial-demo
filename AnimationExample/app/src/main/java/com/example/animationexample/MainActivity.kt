package com.example.animationexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var sharedProfileName: TextView
    private lateinit var sharedProfileImage: ImageView

    //buttons
    private lateinit var sharedElementContainer: LinearLayout
    private lateinit var rippleEffect: Button
    private lateinit var circularActivity: Button
    private lateinit var explodeKotlin: Button
    private lateinit var explodeXml: Button
    private lateinit var fadeKotlin: Button
    private lateinit var fadeXml: Button
    private lateinit var slideKotlin: Button
    private lateinit var slideXml: Button


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initializing all button id
        sharedElementContainer = findViewById(R.id.shared_element_container)
        rippleEffect = findViewById(R.id.ripple_effect_button)
        circularActivity = findViewById(R.id.circular_activity_button)
        explodeKotlin = findViewById(R.id.explode_kotlin)
        explodeXml = findViewById(R.id.explode_xml)
        fadeKotlin = findViewById(R.id.fade_kotlin)
        fadeXml = findViewById(R.id.fade_xml)
        slideKotlin = findViewById(R.id.slide_kotlin)
        slideXml = findViewById(R.id.slide_xml)

        //shared transition implementation
        sharedProfileName = findViewById(R.id.shared_profile_name)
        sharedProfileImage = findViewById(R.id.shared_profile_image)
        sharedElementContainer.setOnClickListener {
            startSharedTransition()
        }

        //calling ripple activity
        rippleEffect.setOnClickListener {
            val intent = Intent(this, RippleEffectActivity::class.java)
            startActivity(intent)
        }

        //circular transition
        circularActivity.setOnClickListener {
            val intent = Intent(this, TransitionActivity::class.java)
            startActivity(intent)
        }

        //explode transition for Activity implementation
        explodeKotlin.setOnClickListener {
            explodeTransitionKotlin()
        }
        explodeXml.setOnClickListener { explodeTransitionXml() }
        fadeKotlin.setOnClickListener {  fadeTransitionKotlin()}
        fadeXml.setOnClickListener { fadeTransitionXml() }
        slideKotlin.setOnClickListener { slideTransitionKotlin() }
        slideXml.setOnClickListener {  slideTransitionXml()}

        startBasicTransition()

    }
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
private fun startBasicTransition(){
    val slide = Slide()
    slide.slideEdge = Gravity.BOTTOM
    slide.duration = 1000
    window.reenterTransition = slide
    window.exitTransition = slide
    window.allowReturnTransitionOverlap = false

}
    private fun startSharedTransition() {
        val pairs = arrayListOf<Pair<View, String>>()
        val pair1 = Pair<View, String>(sharedProfileName, "shared_transition_name")
        val pair2 = Pair<View, String>(sharedProfileImage, "shared_transition_image")// ViewCompat.getTransitionName(viewID)

        pairs.add(pair1)
        pairs.add(pair2)

        val pairArray: Array<Pair<View, String>> = pairs.toTypedArray()

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *(pairArray))
        val intent = Intent(this, TransitionActivity::class.java)
        startActivity(intent, activityOptions.toBundle())

    }

    private fun explodeTransitionKotlin(){
        Log.d(TAG,"explodeTransitionKotlin: started")
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.EXPLODE_KOTLIN)
        intent.putExtra(animationTitle,Constants.TransitionTitle.ExplodeKotlin)
        startActivity(intent,activityOptions.toBundle())
        Log.d(TAG,"explodeTransitionKotlin: ended")
    }
    private fun explodeTransitionXml(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.EXPLODE_XML)
        intent.putExtra(animationTitle,Constants.TransitionTitle.ExplodeXml)
        startActivity(intent,activityOptions.toBundle())
        Log.d(TAG,"explodeTransitionKotlin: ended")
    }
    private fun fadeTransitionKotlin(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.FADE_KOTLIN)
        intent.putExtra(animationTitle,Constants.TransitionTitle.FadeKotlin)
        startActivity(intent,activityOptions.toBundle())
    }
    private fun fadeTransitionXml(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.FADE_XML)
        intent.putExtra(animationTitle,Constants.TransitionTitle.FadeXml)
        startActivity(intent,activityOptions.toBundle())
    }
    private fun slideTransitionKotlin(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.SLIDE_KOTLIN)
        intent.putExtra(animationTitle,Constants.TransitionTitle.SlideKotlin)
        startActivity(intent,activityOptions.toBundle())
    }
    private fun slideTransitionXml(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this,TransitionActivity::class.java)
        intent.putExtra(animationType,Constants.TransitionType.SLIDE_XML)
        intent.putExtra(animationTitle,Constants.TransitionTitle.SlideXml)
        startActivity(intent,activityOptions.toBundle())
    }

}