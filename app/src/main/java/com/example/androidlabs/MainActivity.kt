package com.example.androidlabs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidlabs.pages.CalculatorPage
import com.example.androidlabs.pages.CounterPage
import com.example.androidlabs.pages.MainPage
import com.example.androidlabs.pages.TextEditorPage
import com.example.androidlabs.pages.WelcomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun MyApplication(): Unit {
    val navController = rememberNavController();
    val focusManager = LocalFocusManager.current
    val (page, setPage) = remember { mutableStateOf(Routes.Main.route) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        destination.route?.let { setPage(it) }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(page, fontSize = 24.sp) },
                navigationIcon = navHandler@{
                    focusManager.run { this.clearFocus() }
                    when(page) {
                        Routes.Main.route -> return@navHandler
                        else -> {
                            IconButton({ navController.navigate(Routes.Main.route) }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    navigationIconContentColor = Color.LightGray,
                    actionIconContentColor = Color.LightGray
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavHost(navController = navController, startDestination = Routes.Main.route, Modifier.fillMaxSize()) {
                composable(Routes.Main.route) { MainPage(navController) }
                composable(Routes.Welcome.route) { WelcomePage(navController) }
                composable(Routes.TextEditor.route) { TextEditorPage(navController) }
                composable(Routes.Calculator.route) { CalculatorPage(navController) }
                composable(Routes.Counter.route) { CounterPage(navController) }
            }
        }
    }

}
sealed class Routes(val route: String) {
    data object Main : Routes("Главная")
    data object Welcome : Routes("Приветствие")
    data object TextEditor : Routes("Редактор")
    data object Calculator : Routes("Калькулятор")
    data object Counter : Routes("Счетчик")
}