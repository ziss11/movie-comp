package com.example.movieappcompose.domain.repositories

import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState

interface MovieRepository {
    suspend fun getPopularMovies(apiKey: String): List<MovieModel>
    suspend fun getNowPlayingMovies(apiKey: String): List<MovieModel>
    suspend fun getRecommendedMoviesById(movieId: Int, apiKey: String): List<MovieModel>
    suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieModel
    suspend fun searchMovie(apiKey: String, query: String): List<MovieModel>
}