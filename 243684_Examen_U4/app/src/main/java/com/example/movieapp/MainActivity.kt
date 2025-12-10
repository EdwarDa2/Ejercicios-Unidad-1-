package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.navigation.Screen
import com.example.movieapp.ui.screens.*
import com.example.movieapp.ui.theme._243684_Examen_U4Theme
import com.example.movieapp.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _243684_Examen_U4Theme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val viewModel: MovieViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()
    val selectedMovie by viewModel.selectedMovie.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    var showLoading by remember { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {

            if (!showLoading && currentRoute != Screen.Loading.route) {
                NavigationBar {

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                        label = { Text("Buscar") },
                        selected = currentRoute == Screen.Search.route,
                        onClick = {
                            navController.navigate(Screen.Search.route) {

                                popUpTo(Screen.Search.route) { inclusive = true }
                            }
                        }
                    )


                    NavigationBarItem(
                        icon = {
                            BadgedBox(badge = {
                                if (favorites.isNotEmpty()) {
                                    Badge { Text("${favorites.size}") }
                                }
                            }) {
                                Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                            }
                        },
                        label = { Text("Favoritos") },
                        selected = currentRoute == Screen.Favorites.route,
                        onClick = {
                            navController.navigate(Screen.Favorites.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screen.Loading.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Screen.Loading.route) {
                LoadingScreen(
                    onLoadingComplete = {
                        showLoading = false
                        navController.navigate(Screen.Search.route) {
                            popUpTo(Screen.Loading.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    uiState = uiState,
                    searchQuery = searchQuery,
                    favorites = favorites,
                    onSearchQueryChange = { viewModel.searchMovies(it) },
                    onMovieClick = { imdbId ->
                        viewModel.loadMovieDetails(imdbId)
                        navController.navigate(Screen.Details.createRoute(imdbId))
                    },
                    onSearch = { viewModel.searchMovies(searchQuery) }
                )
            }

            composable("details/{imdbId}") {
                val isFavorite = favorites.any { it.imdbID == selectedMovie?.imdbID }
                DetailsScreen(
                    movie = selectedMovie,
                    isFavorite = isFavorite,
                    onBackClick = { navController.popBackStack() },
                    onFavoriteClick = {
                        selectedMovie?.let { movie -> viewModel.toggleFavorite(movie) }
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    favorites = favorites,
                    onMovieClick = { imdbId ->
                        viewModel.loadMovieDetails(imdbId)
                        navController.navigate(Screen.Details.createRoute(imdbId))
                    },
                    onDeleteClick = { viewModel.removeFavorite(it) }
                )
            }
        }
    }
}