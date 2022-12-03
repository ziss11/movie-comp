package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.SearchMovie
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel(private val searchMovie: SearchMovie) : ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    var searchMovieResult: ResultState<List<Movie>> by mutableStateOf(ResultState.Initial)
        private set

    fun searchMovies(newQuery: String, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            _query.value = newQuery
            searchMovieResult = ResultState.Loading
            delay(1000)

            searchMovieResult = try {
                ResultState.Success(searchMovie.execute(apiKey, _query.value))
            } catch (e: HttpException) {
                ResultState.Initial
            }
        }
    }
}