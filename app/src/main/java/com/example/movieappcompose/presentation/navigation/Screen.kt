package com.example.movieappcompose.presentation.navigation

sealed class Screen(val route: String) {
    object Movie : Screen("movie")
    object Watchlist : Screen("watchlist")
    object About : Screen("about")
    object Detail : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}
