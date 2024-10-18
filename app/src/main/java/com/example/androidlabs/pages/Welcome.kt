package com.example.androidlabs.pages

import android.view.LayoutInflater
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.androidlabs.R

@Composable
internal fun WelcomePage(navController: NavHostController): Unit {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.welcome_page, null)
        },
    )
}