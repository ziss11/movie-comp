package com.example.movieappcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappcompose.domain.usecase.GetWatchlistMovies

class WatchlistViewModel(
    private val getWatchlistMovies: GetWatchlistMovies,
) : ViewModel() {
    fun fetchWatchlistMovies() = getWatchlistMovies.execute()
}