package com.example.movieappcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappcompose.domain.usecase.GetMovieDetail
import com.example.movieappcompose.domain.usecase.GetRecommendedMovies

class DetailViewModel(
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies,
) :
    ViewModel() {
    fun fetchMovieDetail(movieId: Int, apiKey: String) = getMovieDetail.execute(movieId, apiKey)
    fun fetchRecommendationMovies(movieId: Int, apiKey: String) =
        getRecommendedMovies.execute(movieId, apiKey)
}