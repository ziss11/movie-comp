package com.example.movieappcompose.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappcompose.Injection.provideAddWatchlistMovie
import com.example.movieappcompose.Injection.provideGetMovieDetail
import com.example.movieappcompose.Injection.provideGetNowPlayingMovies
import com.example.movieappcompose.Injection.provideGetRecommendedMovies
import com.example.movieappcompose.Injection.provideGetTopRatedMovies
import com.example.movieappcompose.Injection.provideGetWatchlistMovies
import com.example.movieappcompose.Injection.provideGetWatchlistStatus
import com.example.movieappcompose.Injection.provideRemoveWatchlistMovie
import com.example.movieappcompose.Injection.provideSearchMovie
import com.example.movieappcompose.domain.usecase.*
import com.example.movieappcompose.presentation.viewmodels.DetailViewModel
import com.example.movieappcompose.presentation.viewmodels.MovieViewModel
import com.example.movieappcompose.presentation.viewmodels.WatchlistViewModel

class ViewModelFactory private constructor(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getMovieDetail: GetMovieDetail,
    private val getRecommendedMovies: GetRecommendedMovies,
    private val searchMovie: SearchMovie,
    private val getWatchlistMovies: GetWatchlistMovies,
    private val getWatchlistStatus: GetWatchlistStatus,
    private val addWatchlistMovie: AddWatchlistMovie,
    private val removeWatchlistMovie: RemoveWatchlistMovie,
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getTopRatedMovies, getNowPlayingMovies, searchMovie) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                getMovieDetail,
                getRecommendedMovies,
                getWatchlistStatus,
                addWatchlistMovie,
                removeWatchlistMovie
            ) as T
        } else if (modelClass.isAssignableFrom(WatchlistViewModel::class.java)) {
            return WatchlistViewModel(getWatchlistMovies) as T
        }
        throw IllegalArgumentException("Unknown view model class: $modelClass")
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                provideGetTopRatedMovies(context),
                provideGetNowPlayingMovies(context),
                provideGetMovieDetail(context),
                provideGetRecommendedMovies(context),
                provideSearchMovie(context),
                provideGetWatchlistMovies(context),
                provideGetWatchlistStatus(context),
                provideAddWatchlistMovie(context),
                provideRemoveWatchlistMovie(context),
            )
        }.also { instance = it }
    }
}