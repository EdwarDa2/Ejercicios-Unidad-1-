package com.EdwarDa.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.EdwarDa.DarkMode
import com.EdwarDa.StoreDarkMode


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun colorPage(
    navController: NavController, darkModeStore: StoreDarkMode,
    darkMode: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cambio de color de Fondo")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        contentColor(
            darkModeStore = darkModeStore,
            darkMode = darkMode
        )
    }
}

@Composable
fun contentColor(
    darkModeStore: StoreDarkMode,
    darkMode: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DarkMode(
            darkModeStore = darkModeStore,
            darkMode = darkMode
        )
    }
}

