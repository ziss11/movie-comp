package com.example.movieappcompose.data.datasources

import androidx.lifecycle.LiveData
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState

interface MovieRemoteDataSource {
    fun getPopularMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>>
    fun getNowPlayingMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>>
    fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ): LiveData<ResultState<List<MovieModel>>>

    fun getMovieDetail(
        movieId: Int,
        apiKey: String
    ): LiveData<ResultState<MovieModel>>

    fun searchMovie(
        apiKey: String,
        query: String
    ): LiveData<ResultState<List<MovieModel>>>
}

