package com.example.movieappcompose.data.repositories

import com.example.movieappcompose.Injection.provideMovieRemoteDatasource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.domain.repositories.MovieRepository

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun getPopularMovies(apiKey: String) = remoteDataSource.getPopularMovies(apiKey)

    override fun getNowPlayingMovies(apiKey: String) = remoteDataSource.getNowPlayingMovies(apiKey)

    override fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ) = remoteDataSource.getRecommendedMoviesById(movieId, apiKey)

    override fun getMovieDetail(movieId: Int, apiKey: String) =
        remoteDataSource.getMovieDetail(movieId, apiKey)

    override fun searchMovie(
        apiKey: String,
        query: String
    ) = remoteDataSource.searchMovie(apiKey, query)

    companion object {
        private var instance: MovieRepositoryImpl? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MovieRepositoryImpl(provideMovieRemoteDatasource())
        }.also { instance = it }
    }
}