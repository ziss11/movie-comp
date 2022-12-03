package com.example.movieappcompose.domain.repositories

import androidx.lifecycle.LiveData
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.utilities.ResultState

interface MovieRepository {
    // remote
    fun getTopRatedMovies(apiKey: String): LiveData<ResultState<List<Movie>>>
    fun getNowPlayingMovies(apiKey: String): LiveData<ResultState<List<Movie>>>
    fun getRecommendedMoviesById(movieId: Int, apiKey: String): LiveData<ResultState<List<Movie>>>
    fun getMovieDetail(movieId: Int, apiKey: String): LiveData<ResultState<Movie>>
    fun searchMovie(apiKey: String, query: String): LiveData<ResultState<List<Movie>>>

    // local
    fun getWatchlistMovies(): LiveData<ResultState<List<Movie>>>
    fun isWatchlist(id: Int): LiveData<Boolean>
    suspend fun addWatchlistMovie(movie: Movie)
    suspend fun removeWatchlistMovie(id: Int)
}