package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetWatchlistMovies private constructor(private val repository: MovieRepository) {
    suspend fun execute() = repository.getWatchlistMovies()

    companion object {
        private var instance: GetWatchlistMovies? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: GetWatchlistMovies(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}