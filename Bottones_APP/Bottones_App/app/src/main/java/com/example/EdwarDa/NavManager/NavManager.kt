package com.example.EdwarDa.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.EdwarDa.views.DetailsView
import com.example.EdwarDa.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)
        }
    }
}