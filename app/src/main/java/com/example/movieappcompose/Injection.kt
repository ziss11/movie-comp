package com.example.movieappcompose

import android.content.Context
import com.example.movieappcompose.data.datasources.MovieLocalDataSource
import com.example.movieappcompose.data.datasources.MovieLocalDataSourceImpl
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSourceImpl
import com.example.movieappcompose.data.datasources.database.MovieDao
import com.example.movieappcompose.data.datasources.database.MovieDatabase
import com.example.movieappcompose.data.datasources.service.ApiConfig
import com.example.movieappcompose.data.datasources.service.ApiService
import com.example.movieappcompose.data.repositories.MovieRepositoryImpl
import com.example.movieappcompose.domain.repositories.MovieRepository
import com.example.movieappcompose.domain.usecase.*

object Injection {
    // service
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    // database
    fun provideMovieDao(context: Context): MovieDao {
        val db = MovieDatabase.getInstance(context)
        return db.movieDao()
    }

    // data source
    fun provideMovieRemoteDataSource(): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl.getInstance()
    }

    fun provideMovieLocalDataSource(context: Context): MovieLocalDataSource {
        return MovieLocalDataSourceImpl.getInstance(context)
    }

    // repositories
    fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepositoryImpl.getInstance(context)
    }

    // use cases
    fun provideGetMovieDetail(context: Context): GetMovieDetail {
        return GetMovieDetail.getInstance(context)
    }

    fun provideGetNowPlayingMovies(context: Context): GetNowPlayingMovies {
        return GetNowPlayingMovies.getInstance(context)
    }

    fun provideGetTopRatedMovies(context: Context): GetTopRatedMovies {
        return GetTopRatedMovies.getInstance(context)
    }

    fun provideGetRecommendedMovies(context: Context): GetRecommendedMovies {
        return GetRecommendedMovies.getInstance(context)
    }

    fun provideSearchMovie(context: Context): SearchMovie {
        return SearchMovie.getInstance(context)
    }

    fun provideGetWatchlistMovies(context: Context): GetWatchlistMovies {
        return GetWatchlistMovies.getInstance(context)
    }

    fun provideGetWatchlistStatus(context: Context): GetWatchlistStatus {
        return GetWatchlistStatus.getInstance(context)
    }

    fun provideAddWatchlistMovie(context: Context): AddWatchlistMovie {
        return AddWatchlistMovie.getInstance(context)
    }

    fun provideRemoveWatchlistMovie(context: Context): RemoveWatchlistMovie {
        return RemoveWatchlistMovie.getInstance(context)
    }
}