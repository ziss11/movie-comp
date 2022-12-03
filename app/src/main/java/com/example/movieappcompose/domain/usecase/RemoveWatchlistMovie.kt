package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class RemoveWatchlistMovie private constructor(private val repository: MovieRepository) {
    suspend fun execute(id: Int) = repository.removeWatchlistMovie(id)

    companion object {
        private var instance: RemoveWatchlistMovie? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: RemoveWatchlistMovie(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}