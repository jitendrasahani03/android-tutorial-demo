package com.example.lifecycledemo

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.ACTION_WEB_SEARCH
import android.content.Intent.EXTRA_BCC
import android.content.Intent.EXTRA_CC
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_STREAM
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lifecycledemo.databinding.ActivityIntentDemoBinding

class IntentDemo : AppCompatActivity() {
    private val TAG = "IntentDemo"
    lateinit var binding: ActivityIntentDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set xml of this class
        binding = ActivityIntentDemoBinding.inflate(layoutInflater)

        // root layout viewgroup  same as R.layout.activity_intent_demo
        var view = binding.root
        setContentView(view)

        /*To open dialler with number*/
        binding.diallerOpenBtn.setOnClickListener {
            openPhoneDialler()
        }
        binding.webSearchActionButton.setOnClickListener { openWebSearchWitQuery() }
        binding.galleryOpenBtn.setOnClickListener { }

        binding.emailSendBtn.setOnClickListener {
           // sendEmailUsingAction()
      sendFiles()
        }

        binding.viewActionButton.setOnClickListener {
            /*open browser*/
            openBrowserWithGivenLink()
        }
        binding.sendActionButton.setOnClickListener {
            shareUserInfo()
        }
    }

    private fun printInstalledPackages() {
        val packageManager = packageManager
        val installedApp = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        for (application in installedApp) {
            val packageName = application.packageName
            val appName = application.loadLabel(packageManager).toString()
            Log.d(TAG, "Package: $packageName AppName: $appName")
        }
    }

    private fun openPhoneDialler() {
        val phoneNumber = "9800196909"
        val uri = Uri.parse("tel:" + phoneNumber)
        //ACTION_DIAL does not require permission to operation
        val intentDialler = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intentDialler)
    }

    private fun openBrowserWithGivenLink() {
        Log.d(TAG, "OnClick: ACTION_VIEW started")
        val urlToOpen = "https://www.javatpoint.com"
        val intentOpenLink = Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpen))
        val chooseIntent = Intent.createChooser(intentOpenLink, "Choose one app")
        startActivity(chooseIntent)
        //printInstalledPackages()
    }

    private fun shareUserInfo() {
        val intentSend = Intent(Intent.ACTION_SEND, Uri.parse("Hi"))
        intentSend.setType("text/plain")
        val bundle = Bundle()
        bundle.putString("Name", "Jitnedra")
        // val chooserIntent = Intent.createChooser(intentSend,"Choose One App")
        if (intentSend.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intentSend, "Choose One App"))
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmailUsingAction() {
        /*Here we will learn how to design intent to send email
        * 1. To single Recipient
        * 2. To multiple Recipient
        * */
        val emailRecipient = arrayOf<String>("jitendrasahani11@gmail.com")
        val emailBCC = arrayOf<String>("cc1@example.com", "cc2@example.com", "cc3@example.com")
        val emailCC = arrayOf<String>("bcc1@example.com", "bcc2@example.com", "bcc3@example.com")

        val emailMessage = "Hi, this is test email"
        val emailSubject = "Testing Mail Service"

        val emailMailIntent = Intent(ACTION_SEND)
        emailMailIntent.type = "text/plain"
        emailMailIntent.putExtra(EXTRA_EMAIL, emailRecipient)
        emailMailIntent.putExtra(Intent.EXTRA_CC, emailCC)
        emailMailIntent.putExtra(Intent.EXTRA_BCC, emailBCC)
        emailMailIntent.putExtra(EXTRA_SUBJECT, emailSubject)
        emailMailIntent.putExtra(EXTRA_TEXT, emailMessage)
        val chooser = Intent.createChooser(emailMailIntent, "Send Mail")
        startActivity(chooser)
    }

    private fun openCameraToTakeImage() {

    }
    private fun sendFiles(){
        val fileIntent = Intent(ACTION_SEND)
        fileIntent.type = "image/*"
        val imageURL = resources.getDrawable(R.drawable.flower)
        //fileIntent.putExtra(Intent.EXTRA_STREAM,imageURL)
        val choose = Intent.createChooser(fileIntent,"Send Image")
        startActivity(choose)
    }

    private fun openWebSearchWitQuery(){
        val querySearch = "Rahul Gandi"
        val webSearchIntent = Intent(ACTION_WEB_SEARCH)
            .putExtra("query",querySearch)
            startActivity(webSearchIntent)



    }
}
