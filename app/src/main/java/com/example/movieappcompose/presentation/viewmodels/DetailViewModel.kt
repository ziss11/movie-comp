package com.example.movieappcompose.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.usecase.*
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailViewModel(
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies,
    private val getWatchlistStatus: GetWatchlistStatus,
    private val addWatchlistMovie: AddWatchlistMovie,
    private val removeWatchlistMovie: RemoveWatchlistMovie,
) : ViewModel() {
    var movieDetailResult: ResultState<Movie> by mutableStateOf(ResultState.Loading)
        private set

    var recommendationMoviesResult: ResultState<List<Movie>> by mutableStateOf(ResultState.Loading)
        private set

    fun fetchMovieDetail(movieId: Int, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            movieDetailResult = try {
                ResultState.Success(getMovieDetail.execute(movieId, apiKey))
            } catch (e: IOException) {
                ResultState.Error
            } catch (e: HttpException) {
                ResultState.Error
            } finally {
                ResultState.Error
            }
        }
    }

    fun fetchRecommendationMovies(movieId: Int, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            recommendationMoviesResult = try {
                ResultState.Success(getRecommendedMovies.execute(movieId, apiKey))
            } catch (e: IOException) {
                ResultState.Error
            } catch (e: HttpException) {
                ResultState.Error
            } finally {
                ResultState.Error
            }
        }
    }

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