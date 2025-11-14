package com.carlose.scores_unidad3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlose.scores_unidad3.ui.screens.DashboardScreen
import com.carlose.scores_unidad3.ui.screens.StatisticsScreen
import com.carlose.scores_unidad3.ui.screens.StudentFormScreen
import com.carlose.scores_unidad3.ui.theme.Scores_unidad3Theme
import com.carlose.scores_unidad3.viewmodel.SchoolViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scores_unidad3Theme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val viewModel: SchoolViewModel = viewModel()
    val navController = rememberNavController()

    var currentMainTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Gestión") },
                    selected = currentMainTab == 0,
                    onClick = {
                        currentMainTab = 0
                        navController.navigate("dashboard") {
                            popUpTo("dashboard") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("Estadísticas") },
                    selected = currentMainTab == 1,
                    onClick = {
                        currentMainTab = 1
                        navController.navigate("stats") {
                            popUpTo("dashboard") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("dashboard") {
                currentMainTab = 0
                DashboardScreen(
                    viewModel = viewModel,
                    onAddClick = { navController.navigate("add") },
                    onEditClick = { id -> navController.navigate("edit/$id") }
                )
            }

            composable("add") {
                StudentFormScreen(
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "edit/{studentId}",
                arguments = listOf(navArgument("studentId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("studentId")
                StudentFormScreen(
                    studentId = id,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }


            composable("stats") {
                currentMainTab = 1
                StatisticsScreen(viewModel = viewModel)
            }
        }
    }
}