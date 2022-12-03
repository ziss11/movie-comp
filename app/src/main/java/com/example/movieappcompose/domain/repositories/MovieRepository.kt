package com.example.movieappcompose.domain.repositories

import androidx.lifecycle.LiveData
import com.example.movieappcompose.domain.entities.Movie

interface MovieRepository {
    // remote
    suspend fun getTopRatedMovies(apiKey: String): List<Movie>
    suspend fun getNowPlayingMovies(apiKey: String): List<Movie>
    suspend fun getRecommendedMoviesById(movieId: Int, apiKey: String): List<Movie>
    suspend fun getMovieDetail(movieId: Int, apiKey: String): Movie
    suspend fun searchMovie(apiKey: String, query: String): List<Movie>

    // local
    suspend fun getWatchlistMovies(): List<Movie>
    fun isWatchlist(id: Int): LiveData<Boolean>
    suspend fun addWatchlistMovie(movie: Movie)
    suspend fun removeWatchlistMovie(id: Int)
}