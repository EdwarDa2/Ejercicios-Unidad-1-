package com.example.EdwarDa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.EdwarDa.ui.theme.Bottones_AppTheme
import com.example.EdwarDa.NavManager.NavManager

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bottones_AppTheme (darkTheme = true) {
                NavManager()
            }
        }
    }

}