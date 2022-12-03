package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection.provideMovieRepository
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetNowPlayingMovies private constructor(private val repository: MovieRepository) {
    fun execute(apiKey: String) = repository.getNowPlayingMovies(apiKey)

    companion object {
        private var instance: GetNowPlayingMovies? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: GetNowPlayingMovies(provideMovieRepository(context))
        }.also { instance = it }
    }
}