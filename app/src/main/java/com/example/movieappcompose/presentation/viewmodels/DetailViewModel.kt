package com.example.movieappcompose.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.domain.usecase.GetMovieDetail
import com.example.movieappcompose.domain.usecase.GetRecommendedMovies
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailViewModel(
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies,
) :
    ViewModel() {
    var movieDetailResult: ResultState<MovieModel> by mutableStateOf(ResultState.Loading)
        private set

    var recommendationMoviesResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
        private set

    fun fetchMovieDetail(movieId: Int, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            movieDetailResult = try {
                ResultState.Success(getMovieDetail.execute(movieId, apiKey))
            } catch (e: IOException) {
                ResultState.Error(e.toString())
            } catch (e: HttpException) {
                ResultState.Error(e.toString())
            } finally {
                ResultState.Error("Data Empty")
            }
        }
    }


    fun fetchRecommendationMovies(movieId: Int, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            recommendationMoviesResult = try {
                ResultState.Success(getRecommendedMovies.execute(movieId, apiKey))
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