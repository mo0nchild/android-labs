package com.example.androidlabs.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.indices
import androidx.navigation.NavController
import com.example.androidlabs.R
import com.example.androidlabs.Routes

val operators = arrayOf('/', '*', '+', '-')
internal fun calculator(input: String): Double {
    val div = input.indexOfFirst { it == '/' }
    val multi = input.indexOfFirst { it == '*' }
    val plus = input.indexOfFirst { it == '+' }
    val minus = input.indexOfFirst { it == '-' }
    return when {
        plus != -1 -> {
            calculator(input.substring(0 until plus)) + calculator(input.substring(plus + 1..input.lastIndex))
        }
        minus != -1 -> {
            calculator(input.substring(0 until minus)) - calculator(input.substring(minus + 1..input.lastIndex))
        }
        div != -1 -> {
            calculator(input.substring(0 until div)) / calculator(input.substring(div + 1..input.lastIndex))
        }
        multi != -1 -> {
            calculator(input.substring(0 until multi)) * calculator(input.substring(multi + 1..input.lastIndex))
        }
        else -> input.toDouble()
    }
}
@SuppressLint("SetTextI18n")
@Composable
internal fun CalculatorPage(navController: NavController): Unit {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.calculator_page, null)
        },
        update = { view ->
            var calculated = true
            val resultView = view.findViewById<TextView>(R.id.resultTextView)
            val buttons = view.findViewById<GridLayout>(R.id.buttons)
            buttons.children.filterIsInstance<Button>()
                .filter { it.text != "=" && it.text != "." }
                .forEach { item ->
                    item.setOnClickListener {
                        resultView.text = if (calculated) item.text else {
                            if(item.text in operators.map { it.toString() } && resultView.text.lastOrNull() in arrayOf(null, *operators)) {
                                resultView.text
                            }
                            else "${resultView.text}${item.text}"
                        }
                        calculated = false
                    }
            }
            view.findViewById<Button>(R.id.buttonNumEquals).setOnClickListener {
                try {
                    val result = calculator(resultView.text.toString())
                    println("result = result")
                    resultView.text = "=${result}"
                }
                catch(error: Exception) {
                    println(error.message)
                }
                calculated = true
            }
            view.findViewById<Button>(R.id.buttonNumDot).setOnClickListener {
                val lastIndex = resultView.text.indexOfLast { it in operators }
                val between = resultView.text.drop(if(lastIndex == -1) 0 else lastIndex + 1)
                if(between.isNotEmpty() && !between.any { it == '.' }) {
                    resultView.text = "${resultView.text}."
                }
            }
            view.findViewById<Button>(R.id.buttonClear).setOnClickListener {
                resultView.text = "0"
                calculated = true
            }
        }
    )
}