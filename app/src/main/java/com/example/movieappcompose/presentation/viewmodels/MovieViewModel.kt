package com.example.movieappcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetPopularMovies

class MovieViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {
    fun fetchPopularMovies(apiKey: String) = getPopularMovies.execute(apiKey)
    fun fetchNowPlayingMovies(apiKey: String) = getNowPlayingMovies.execute(apiKey)
}