package com.example.movieappcompose

import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSourceImpl
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

    // data source
    fun provideMovieRemoteDatasource(): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl.getInstance()
    }

    // repositories
    fun provideMovieRepository(): MovieRepository {
        return MovieRepositoryImpl.getInstance()
    }

    // use cases
    fun provideGetMovieDetail(): GetMovieDetail {
        return GetMovieDetail.getInstance()
    }

    fun provideGetNowPlayingMovies(): GetNowPlayingMovies {
        return GetNowPlayingMovies.getInstance()
    }

    fun provideGetPopularMovies(): GetPopularMovies {
        return GetPopularMovies.getInstance()
    }

    fun provideGetRecommendedMovies(): GetRecommendedMovies {
        return GetRecommendedMovies.getInstance()
    }

    fun provideSearchMovie(): SearchMovie {
        return SearchMovie.getInstance()
    }
}