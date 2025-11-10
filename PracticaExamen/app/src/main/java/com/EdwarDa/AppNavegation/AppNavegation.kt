package com.EdwarDa.AppNavegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.EdwarDa.Screens.formulari
import com.EdwarDa.Screens.colorPage
import com.EdwarDa.Screens.content
import com.EdwarDa.MainViewModel
import com.EdwarDa.StoreDarkMode

@Composable
fun AppNavegation(
    darkModeStore: StoreDarkMode,
    darkMode: Boolean
) {
    val navController = rememberNavController()


    NavHost(
        navController = navController, startDestination = AppScreens.Content.route
    ) {
        composable(route = AppScreens.Content.route) {
            content(navController)
        }
        composable(route = AppScreens.Formulari.route) {
            val viewModel: MainViewModel = viewModel()
            formulari(navController, viewModel)
        }
        composable(route = AppScreens.ColorPage.route) {

            colorPage(
                navController, darkModeStore = darkModeStore,
                darkMode = darkMode
            )

        }
    }
}
