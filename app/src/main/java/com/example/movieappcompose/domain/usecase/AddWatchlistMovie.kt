package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.repositories.MovieRepository

class AddWatchlistMovie private constructor(private val repository: MovieRepository) {
    suspend fun execute(movie: Movie) = repository.addWatchlistMovie(movie)

    companion object {
        private var instance: AddWatchlistMovie? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AddWatchlistMovie(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}