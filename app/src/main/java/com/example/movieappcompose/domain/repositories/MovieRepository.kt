package com.example.movieappcompose.domain.repositories

import androidx.lifecycle.LiveData
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState

interface MovieRepository {
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