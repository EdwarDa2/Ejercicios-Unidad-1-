package com.example.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object Loading : Screen("loading")
    object Search : Screen("search")
    object Details : Screen("details/{imdbId}") {
        fun createRoute(imdbId: String) = "details/$imdbId"
    }
    object Favorites : Screen("favorites")
}