package com.example.coroutinesappa.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.coroutinesappa.presentation.viewModel.CoroutinesViewModel
import com.example.coroutinesappa.presentation.views.ButtonsView
import com.example.coroutinesappa.presentation.views.DashboardView


@Composable
fun NavManager(viewModel: CoroutinesViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "DashboardView"
    ){
        composable(
            "DashboardView"
        ){
            DashboardView(navController)
        }

        composable(
            "ButtonsView"
        ){
            ButtonsView(navController, viewModel)
        }

    }
}

