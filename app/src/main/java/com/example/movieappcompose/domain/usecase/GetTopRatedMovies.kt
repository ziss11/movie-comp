package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetTopRatedMovies private constructor(private val repository: MovieRepository) {
    fun execute(apiKey: String) = repository.getTopRatedMovies(apiKey)

    companion object {
        private var instance: GetTopRatedMovies? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: GetTopRatedMovies(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}