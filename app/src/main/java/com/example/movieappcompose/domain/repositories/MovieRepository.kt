package com.example.movieappcompose.domain.repositories

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
    suspend fun isWatchlist(id: Int): Boolean
    suspend fun addWatchlistMovie()
    suspend fun remoteWatchlistMovie()
}