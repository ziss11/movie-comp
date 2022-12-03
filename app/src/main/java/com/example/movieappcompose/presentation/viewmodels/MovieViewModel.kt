package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetTopRatedMovies
import com.example.movieappcompose.domain.usecase.SearchMovie
import com.example.movieappcompose.utilities.ResultState

class MovieViewModel(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val searchMovie: SearchMovie,
) : ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchMovies(
        newQuery: String,
        apiKey: String = BuildConfig.API_KEY
    ): LiveData<ResultState<List<Movie>>> {
        _query.value = newQuery
        return searchMovie.execute(apiKey, _query.value)
    }

    fun fetchTopRatedMovies(apiKey: String = BuildConfig.API_KEY) =
        getTopRatedMovies.execute(apiKey)

    fun fetchNowPlayingMovies(apiKey: String = BuildConfig.API_KEY) =
        getNowPlayingMovies.execute(apiKey)
}