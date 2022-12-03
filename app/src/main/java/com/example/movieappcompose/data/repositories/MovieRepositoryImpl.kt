package com.example.movieappcompose.data.repositories

import android.content.Context
import com.example.movieappcompose.Injection.provideMovieLocalDataSource
import com.example.movieappcompose.Injection.provideMovieRemoteDataSource
import com.example.movieappcompose.data.datasources.MovieLocalDataSource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.repositories.MovieRepository

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override suspend fun getTopRatedMovies(apiKey: String) =
        remoteDataSource.getTopRatedMovies(apiKey).map { it.toEntity() }

    override suspend fun getNowPlayingMovies(apiKey: String) =
        remoteDataSource.getNowPlayingMovies(apiKey).map { it.toEntity() }

    override suspend fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ) = remoteDataSource.getRecommendedMoviesById(movieId, apiKey).map { it.toEntity() }

    override suspend fun getMovieDetail(movieId: Int, apiKey: String) =
        remoteDataSource.getMovieDetail(movieId, apiKey).toEntity()

    override suspend fun searchMovie(
        apiKey: String,
        query: String
    ) = remoteDataSource.searchMovie(apiKey, query).map { it.toEntity() }

    override suspend fun getWatchlistMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun isWatchlist(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addWatchlistMovie() {
        TODO("Not yet implemented")
    }

    override suspend fun remoteWatchlistMovie() {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: MovieRepositoryImpl? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MovieRepositoryImpl(
                provideMovieRemoteDataSource(),
                provideMovieLocalDataSource(context)
            )
        }.also { instance = it }
    }
}