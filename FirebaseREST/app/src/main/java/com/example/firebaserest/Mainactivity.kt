package com.example.firebaserest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.firebaserest.ui.theme.FirebaseRESTTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(context = this)

        setContent {
            FirebaseRESTTheme {
                TaskScreen(viewModel = viewModel)
            }
        }
    }
}
