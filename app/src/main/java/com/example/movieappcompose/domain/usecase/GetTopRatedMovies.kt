package com.example.movieappcompose.domain.usecase

import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetTopRatedMovies private constructor(private val repository: MovieRepository) {
    suspend fun execute(apiKey: String) = repository.getTopRatedMovies(apiKey)

    companion object {
        private var instance: GetTopRatedMovies? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GetTopRatedMovies(Injection.provideMovieRepository())
        }.also { instance = it }
    }
}