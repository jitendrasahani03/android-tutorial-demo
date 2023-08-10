package com.example.firstprogram

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    var teamAScore: Int = 0
    var teamBScore: Int = 0
    var quantity:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var textView = TextView(this)
        textView.setText("Hi")
        textView.setTextSize(20F)
        setContentView(R.layout.coffeeorder)
        //setContentView(textView)
    }

    public fun submitOrder(view: View) {
        var totalPrice = quantity * 2
        if (quantity==0){
            Toast.makeText(this,"Quantity cannot be zero",Toast.LENGTH_LONG).show()
        }
        var toppingDetail = ""
        var hasBrownSugar = findViewById<CheckBox>(R.id.brown_sugar_check_box)
        var hasHoney = findViewById<CheckBox>(R.id.honey_check_box)
        var hasCocoaPowder = findViewById<CheckBox>(R.id.cocoa_powder_check_box)
        var nameEditText = findViewById<EditText>(R.id.name_edit_view)

        if(hasBrownSugar.isChecked){
            totalPrice = totalPrice + 1
            toppingDetail = toppingDetail+ "\nBrown sugar $1"
        }
        if(hasHoney.isChecked){
            totalPrice = totalPrice + 2
            toppingDetail = toppingDetail + "\nHoney $2"
        }
        if(hasCocoaPowder.isChecked){
            totalPrice = totalPrice + 3
            toppingDetail = toppingDetail + "\nCocoa Power $3"
        }

        //Toast.makeText(this,"$toppingDetail",Toast.LENGTH_LONG).show()
        orderSummary(totalPrice, nameEditText.text.toString().trim(),toppingDetail)
    }

    public fun increment(view: View) {
        quantity++
        if(quantity>=10){
            Toast.makeText(this,"Quantity cannot be greater than 10",Toast.LENGTH_LONG).show()
        }
        display(quantity)
        //price(quantity*2)
    }

    public fun decrement(view: View) {
        if (quantity <= 0) {
            quantity = 0
            Toast.makeText(this,"Quantity cannot be less than 1",Toast.LENGTH_LONG).show()
        } else {
            quantity--
        }
        display(quantity)

    }

    private fun display(quantity: Int) {
        var quantityTextView = findViewById<TextView>(R.id.quantity_text_view)
        quantityTextView.text = quantity.toString()
    }

    private fun orderSummary(price: Int, name: String,topping:String) {


        var priceTextView = findViewById<TextView>(R.id.order_summary_text_view)
        var priceString = "Name: $name \nTopping:$topping \nQuantity: $quantity\n" + "Total: ${
            NumberFormat.getCurrencyInstance().format(price)
        }"
        priceTextView.text = priceString

        sendMail(priceString)
    }
    private fun sendMail(message:String) {
        val addresses =
            arrayListOf<String>("jitendrasahani1998@gmail.com", "temporaryapp3@gmail.com")
        val subject = "Order Summary"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
           setData(Uri.parse("mailto:jitendrasahani1998@gmail.com"))
            putExtra(Intent.EXTRA_EMAIL, "jitendrasahani1998@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
            putExtra(Intent.EXTRA_CC,"hello@gmail.com")
        }

        //to check whether there is receiver or not

        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }

    }
    public fun addThreeForTeamA(view: View) {
        teamAScore += 3
        teamAScore(teamAScore)
    }

    public fun addTwoForTeamA(view: View) {
        teamAScore += 2
        teamAScore(teamAScore)
    }

    public fun addOneForTeamA(view: View) {
        teamAScore++
        teamAScore(teamAScore)
    }

    private fun teamAScore(scoreA: Int) {
        var teamAScoreTextView = findViewById<TextView>(R.id.team_a_score)
        teamAScoreTextView.text = scoreA.toString()
    }

    public fun addThreeForTeamB(view: View) {
        teamBScore += 3
        teamBScore(teamBScore)
    }

    public fun addTwoForTeamB(view: View) {
        teamBScore = teamBScore + 2
        teamBScore(teamBScore)
    }

    public fun addOneForTeamB(view: View) {
        teamBScore++
        teamBScore(teamBScore)
    }

    private fun teamBScore(scoreB: Int) {
        var teamBScoreTextView = findViewById<TextView>(R.id.team_b_score)
        teamBScoreTextView.text = scoreB.toString()
    }

    public fun resetScore(view: View) {
        teamAScore = 0
        teamBScore = 0
        teamAScore(teamAScore)
        teamBScore(teamAScore)
    }

}