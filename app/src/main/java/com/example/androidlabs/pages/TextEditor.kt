package com.example.androidlabs.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.androidlabs.R


@SuppressLint("SetTextI18n")
@Composable
internal fun TextEditorPage(navController: NavController): Unit {
    val focusManager = LocalFocusManager.current
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.texteditor_page, null)
        },
        update = { view ->
            val textEditor = view.findViewById<EditText>(R.id.inputTextEdit);
            textEditor.setOnEditorActionListener handler@{ textView, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        view.findViewById<TextView>(R.id.labelTextField).text = "Привет, ${textEditor.text}"
                        textEditor.text.clear()

                        focusManager.clearFocus()
                        return@handler true
                    }
                    return@handler false
                }
        }
    )
}