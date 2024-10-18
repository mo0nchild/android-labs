package com.example.androidlabs.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import androidx.navigation.NavController
import com.example.androidlabs.R

@SuppressLint("SetTextI18n")
@Composable
internal fun CounterPage(navController: NavController): Unit {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.counter_page, null)
        },
        update = { view ->
            val counterView = view.findViewById<TextView>(R.id.textView2)
            var counter = 0
            counterView.text = "Counter: $counter"
            view.findViewById<Button>(R.id.button5).setOnClickListener {
                counterView.text = "Counter: ${++counter}"
            }
        }
    )
}