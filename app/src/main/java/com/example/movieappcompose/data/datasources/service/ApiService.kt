package com.example.movieappcompose.data.datasources.service

import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.data.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponse

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponse

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendedMoviesById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieResponse

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieModel

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): MovieResponse
}