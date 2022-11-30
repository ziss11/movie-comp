package com.example.movieappcompose.ui.navigation

sealed class Screen(val route: String){
    object Movie: Screen("movie")
    object Series: Screen("series")
    object Profile: Screen("profile")
    object Watchlist: Screen("watchlist")
    object Detail: Screen("home/{movieId}"){
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}
