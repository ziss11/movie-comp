package com.example.movieappcompose.data.datasources.service

import com.example.movieappcompose.data.models.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular?api_key={apiKey}")
    fun getPopularMovies(
        @Path("apiKey") apiKey: String,
    ): Call<List<MovieModel>>

    @GET("movie/now_playing?api_key={apiKey}")
    fun getNowPlayingMovies(
        @Path("apiKey") apiKey: String,
    ): Call<List<MovieModel>>

    @GET("movie/{movieId}/recommendations?api_key={apiKey}")
    fun getRecommendedMoviesById(
        @Path("movieId") movieId: Int,
        @Path("apiKey") apiKey: String,
    ): Call<List<MovieModel>>

    @GET("movie/{movieId}?api_key={apiKey}")
    fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Path("apiKey") apiKey: String,
    ): Call<MovieModel>

    @GET("search/movie?api_key={apiKey}&query={query}")
    fun searchMovie(
        @Path("apiKey") apiKey: String,
        @Path("apiKey") query: String,
    ): Call<List<MovieModel>>
}