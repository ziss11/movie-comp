package com.example.movieappcompose.data.datasources

import com.example.movieappcompose.Injection.provideApiService
import com.example.movieappcompose.data.datasources.service.ApiService
import com.example.movieappcompose.data.models.MovieModel

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(apiKey: String): List<MovieModel>
    suspend fun getNowPlayingMovies(apiKey: String): List<MovieModel>
    suspend fun getRecommendedMoviesById(movieId: Int, apiKey: String): List<MovieModel>
    suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieModel
    suspend fun searchMovie(apiKey: String, query: String): List<MovieModel>
}

class MovieRemoteDataSourceImpl private constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String): List<MovieModel> {
        return apiService.getPopularMovies(apiKey).results ?: listOf()
    }

    override suspend fun getNowPlayingMovies(apiKey: String): List<MovieModel> {
        return apiService.getNowPlayingMovies(apiKey).results ?: listOf()
    }

    override suspend fun getRecommendedMoviesById(movieId: Int, apiKey: String): List<MovieModel> {
        return apiService.getRecommendedMoviesById(movieId, apiKey).results ?: listOf()
    }

    override suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieModel {
        return apiService.getMovieDetail(movieId, apiKey)
    }

    override suspend fun searchMovie(apiKey: String, query: String): List<MovieModel> {
        return apiService.searchMovie(apiKey, query).results ?: listOf()
    }

    companion object {
        private var instance: MovieRemoteDataSourceImpl? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MovieRemoteDataSourceImpl(provideApiService())
        }.also { instance = it }
    }
}