package com.example.androidlabs.pages

import android.view.LayoutInflater
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.androidlabs.R
import com.example.androidlabs.Routes

@Composable
internal fun MainPage(navController: NavController): Unit {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.main_page, null)
        },
        update = { view ->
            view.findViewById<Button>(R.id.button).setOnClickListener {
                navController.navigate(Routes.Welcome.route)
            }
            view.findViewById<Button>(R.id.button2).setOnClickListener {
                navController.navigate(Routes.TextEditor.route)
            }
            view.findViewById<Button>(R.id.button3).setOnClickListener {
                navController.navigate(Routes.Calculator.route)
            }
            view.findViewById<Button>(R.id.button4).setOnClickListener {
                navController.navigate(Routes.Counter.route)
            }
        }
    )
}