package com.example.movieappcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappcompose.Injection.provideGetMovieDetail
import com.example.movieappcompose.Injection.provideGetNowPlayingMovies
import com.example.movieappcompose.Injection.provideGetRecommendedMovies
import com.example.movieappcompose.Injection.provideGetTopRatedMovies
import com.example.movieappcompose.domain.usecase.GetMovieDetail
import com.example.movieappcompose.domain.usecase.GetNowPlayingMovies
import com.example.movieappcompose.domain.usecase.GetRecommendedMovies
import com.example.movieappcompose.domain.usecase.GetTopRatedMovies
import com.example.movieappcompose.presentation.viewmodels.DetailViewModel
import com.example.movieappcompose.presentation.viewmodels.MovieViewModel

class ViewModelFactory private constructor(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getTopRatedMovies, getNowPlayingMovies) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(getMovieDetail, getRecommendedMovies) as T
        }
        throw IllegalArgumentException("Unknown view model class: ${modelClass}")
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                provideGetTopRatedMovies(),
                provideGetNowPlayingMovies(),
                provideGetMovieDetail(),
                provideGetRecommendedMovies(),
            )
        }.also { instance = it }
    }
}