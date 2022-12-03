package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.GetWatchlistMovies
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val getWatchlistMovies: GetWatchlistMovies,
) : ViewModel() {
    var watchlistMoviesResult: ResultState<List<Movie>> by mutableStateOf(ResultState.Loading)
        private set

    init {
        fetchWatchlistMovies()
    }

    private fun fetchWatchlistMovies() {
        viewModelScope.launch {
            watchlistMoviesResult = try {
                ResultState.Success(getWatchlistMovies.execute())
            } catch (e: Exception) {
                ResultState.Error
            } finally {
                ResultState.Error
            }
        }
    }
}