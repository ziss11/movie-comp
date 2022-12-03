package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetTopRatedMovies
import com.example.movieappcompose.domain.usecase.SearchMovie
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val searchMovie: SearchMovie,
) : ViewModel() {
    var nowPlayingMoviesResult: ResultState<List<Movie>> by mutableStateOf(ResultState.Loading)
        private set

    var searchMovieResult: ResultState<List<Movie>> by mutableStateOf(ResultState.Initial)

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    init {
        fetchNowPlayingMovies()
    }

    fun searchMovies(newQuery: String, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            _query.value = newQuery
            ResultState.Loading
            delay(1000)

            searchMovieResult = try {
                ResultState.Success(searchMovie.execute(apiKey, _query.value))
            } catch (e: HttpException) {
                ResultState.Initial
            }
        }
    }

    fun fetchTopRatedMovies(apiKey: String = BuildConfig.API_KEY) =
        getTopRatedMovies.execute(apiKey)

    private fun fetchNowPlayingMovies(apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            nowPlayingMoviesResult = try {
                ResultState.Success(getNowPlayingMovies.execute(apiKey))
            } catch (e: IOException) {
                ResultState.Error
            } catch (e: HttpException) {
                ResultState.Error
            } finally {
                ResultState.Error
            }
        }
    }
}