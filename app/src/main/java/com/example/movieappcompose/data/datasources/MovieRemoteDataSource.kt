package com.example.movieappcompose.data.datasources

import com.example.movieappcompose.Injection.provideApiService
import com.example.movieappcompose.data.datasources.service.ApiService
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.data.models.MovieResponse
import retrofit2.Call

interface MovieRemoteDataSource {
    fun getTopRatedMovies(apiKey: String): Call<MovieResponse>
    suspend fun getNowPlayingMovies(apiKey: String): List<MovieModel>
    suspend fun getRecommendedMoviesById(movieId: Int, apiKey: String): List<MovieModel>
    suspend fun getMovieDetail(movieId: Int, apiKey: String): MovieModel
    suspend fun searchMovie(apiKey: String, query: String): List<MovieModel>
}

class MovieRemoteDataSourceImpl private constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {
    override fun getTopRatedMovies(apiKey: String) =
        apiService.getTopRatedMovies(apiKey)

    override suspend fun getNowPlayingMovies(apiKey: String) =
        apiService.getNowPlayingMovies(apiKey).results ?: listOf()

    override suspend fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ) = apiService.getRecommendedMoviesById(movieId, apiKey).results ?: listOf()

    override suspend fun getMovieDetail(movieId: Int, apiKey: String) =
        apiService.getMovieDetail(movieId, apiKey)

    override suspend fun searchMovie(apiKey: String, query: String) =
        apiService.searchMovie(apiKey, query).results ?: listOf()

    companion object {
        private var instance: MovieRemoteDataSourceImpl? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MovieRemoteDataSourceImpl(provideApiService())
        }.also { instance = it }
    }
}