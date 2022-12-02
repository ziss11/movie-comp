package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetTopRatedMovies
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {
    var topRatedMoviesResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
        private set

    var nowPlayingMoviesResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
        private set

    init {
        fetchPopularMovies()
        fetchNowPlayingMovies()
    }

    private fun fetchPopularMovies(apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            topRatedMoviesResult = try {
                ResultState.Success(getTopRatedMovies.execute(apiKey))
            } catch (e: IOException) {
                ResultState.Error(e.toString())
            } catch (e: HttpException) {
                ResultState.Error(e.toString())
            } finally {
                ResultState.Error("Data Empty")
            }
        }
    }

    private fun fetchNowPlayingMovies(apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            nowPlayingMoviesResult = try {
                ResultState.Success(getNowPlayingMovies.execute(apiKey))
            } catch (e: IOException) {
                ResultState.Error(e.toString())
            } catch (e: HttpException) {
                ResultState.Error(e.toString())
            } finally {
                ResultState.Error("Data Empty")
            }
        }
    }
}