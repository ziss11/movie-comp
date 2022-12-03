package com.example.movieappcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies,
    private val getWatchlistStatus: GetWatchlistStatus,
    private val addWatchlistMovie: AddWatchlistMovie,
    private val removeWatchlistMovie: RemoveWatchlistMovie,
) : ViewModel() {
    fun fetchMovieDetail(movieId: Int, apiKey: String = BuildConfig.API_KEY) =
        getMovieDetail.execute(movieId, apiKey)

    fun fetchRecommendationMovies(movieId: Int, apiKey: String = BuildConfig.API_KEY) =
        getRecommendedMovies.execute(movieId, apiKey)

    fun getWatchlistStatus(id: Int) = getWatchlistStatus.execute(id)

    fun addMovieToWatchlist(movie: Movie) {
        viewModelScope.launch {
            addWatchlistMovie.execute(movie)
        }
    }

    fun removeMovieFromWatchlist(id: Int) {
        viewModelScope.launch {
            removeWatchlistMovie.execute(id)
        }
    }
}