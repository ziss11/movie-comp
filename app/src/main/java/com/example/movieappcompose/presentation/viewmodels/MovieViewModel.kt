package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetPopularMovies
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {
    var popularMoviesResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
        private set

    var getNowPlayingMoviesResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
        private set

    init {
        fetchPopularMovies(BuildConfig.API_KEY)
    }

    private fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            popularMoviesResult = ResultState.Loading
            popularMoviesResult = try {
                ResultState.Success(getPopularMovies.execute(apiKey))
            } catch (e: IOException) {
                ResultState.Error(e.toString())
            } catch (e: HttpException) {
                ResultState.Error(e.toString())
            } finally {
                ResultState.Error("Data Empty")
            }
        }
    }

    fun fetchNowPlayingMovies(apiKey: String) {
        fun fetchPopularMovies(apiKey: String) {
            viewModelScope.launch {
                getNowPlayingMoviesResult = ResultState.Loading
                getNowPlayingMoviesResult = try {
                    ResultState.Success(getPopularMovies.execute(apiKey))
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
}