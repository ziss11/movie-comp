package com.example.movieappcompose.domain.usecase

import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetPopularMovies private constructor(private val repository: MovieRepository) {
    fun execute(apiKey: String) = repository.getPopularMovies(apiKey);

    companion object {
        private var instance: GetPopularMovies? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GetPopularMovies(Injection.provideMovieRepository())
        }.also { instance = it }
    }
}