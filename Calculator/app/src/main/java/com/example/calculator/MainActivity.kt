package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_OPERAND1 = "Operand1"


class MainActivity : AppCompatActivity() {

    private var operand1: Double? = null
    private var pendingOperation = "="
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // setContentView(R.layout.activity_main)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            binding.number.append(b.text)
        }

        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = binding.number.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: java.lang.NumberFormatException) {
                binding.number.setText("")
            }
            pendingOperation = op
            binding.operation.text = pendingOperation
        }
        binding.buttonDivide.setOnClickListener(opListener)
        binding.buttonMultiply.setOnClickListener(opListener)
        binding.buttonMinus.setOnClickListener(opListener)
        binding.buttonAdd.setOnClickListener(opListener)
        binding.buttonEquals.setOnClickListener(opListener)

        binding.buttonNeg?.setOnClickListener { v ->
            val value = binding.number.text.toString()
            if (value.isEmpty()) {
                binding.number.setText("-")

            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    binding.number.setText(doubleValue.toString())
                } catch (e: NumberFormatException) {
                    binding.number.setText("")
                }
            }

        }
        binding.buttonClear?.setOnClickListener { v ->
            binding.result.setText("")
            binding.number.setText("")
            operand1 = null
            binding.operation.setText("")
            pendingOperation = "="
        }
    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
                "=" -> operand1 = value
                "/" -> {
                    operand1 = if (value == 0.0) {
                        Double.NaN
                    } else {
                        operand1!! / value
                    }
                }
            }
        }
        binding.result.setText(operand1.toString())
        binding.number.text.clear()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
        binding.operation.text = pendingOperation
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
        }

        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }
}