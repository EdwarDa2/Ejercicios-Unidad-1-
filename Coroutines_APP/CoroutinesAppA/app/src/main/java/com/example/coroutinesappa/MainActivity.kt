package com.example.coroutinesappa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutinesappa.presentation.navegation.NavManager
import com.example.coroutinesappa.presentation.viewModel.CoroutinesViewModel
import com.example.coroutinesappa.ui.theme.CoroutinesAppATheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CoroutinesViewModel by viewModels()
            CoroutinesAppATheme {
                NavManager(viewModel)
            }
        }
    }
}


