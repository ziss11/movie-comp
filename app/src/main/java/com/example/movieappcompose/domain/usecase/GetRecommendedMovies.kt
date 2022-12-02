package com.example.movieappcompose.domain.usecase

import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetRecommendedMovies private constructor(private val repository: MovieRepository) {
    fun execute(movieId: Int, apiKey: String) =
        repository.getRecommendedMoviesById(movieId, apiKey);

    companion object {
        private var instance: GetRecommendedMovies? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GetRecommendedMovies(Injection.provideMovieRepository())
        }.also { instance = it }
    }
}