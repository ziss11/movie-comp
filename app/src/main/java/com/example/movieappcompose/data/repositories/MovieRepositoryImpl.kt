package com.example.movieappcompose.data.repositories

import com.example.movieappcompose.Injection.provideMovieRemoteDatasource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.domain.repositories.MovieRepository

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: MovieRemoteDataSource
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

    companion object {
        private var instance: MovieRepositoryImpl? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MovieRepositoryImpl(provideMovieRemoteDatasource())
        }.also { instance = it }
    }
}