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
import androidx.navigation.NavController
import com.EdwarDa.AppNavegation.AppNavegation
import com.EdwarDa.AppNavegation.AppScreens
import com.EdwarDa.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun content(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pagina principal ") },
            )
        }) {
        contentData(navController)
    }

}

@Composable
fun contentData(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(AppScreens.Formulari.route) }) { Text("Formulario") }
        Button(onClick = { navController.navigate(AppScreens.ColorPage.route) }) { Text("Cambio de color") }
    }
}