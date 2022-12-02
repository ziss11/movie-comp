package com.example.movieappcompose.domain.repositories

import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState

interface MovieRepository {
    fun getPopularMovies(apiKey: String): ResultState<List<MovieModel>>
    fun getNowPlayingMovies(apiKey: String): ResultState<List<MovieModel>>
    fun getRecommendedMoviesById(movieId: Int, apiKey: String): ResultState<List<MovieModel>>
    fun getMovieDetail(movieId: Int, apiKey: String): ResultState<MovieModel>
    fun searchMovie(apiKey: String, query: String): ResultState<List<MovieModel>>
}