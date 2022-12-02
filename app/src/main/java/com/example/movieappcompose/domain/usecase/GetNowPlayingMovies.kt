package com.example.movieappcompose.domain.usecase

import com.example.movieappcompose.Injection.provideMovieRepository
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetNowPlayingMovies private constructor(private val repository: MovieRepository) {
    fun execute(apiKey: String) = repository.getNowPlayingMovies(apiKey);

    companion object {
        private var instance: GetNowPlayingMovies? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GetNowPlayingMovies(provideMovieRepository())
        }.also { instance = it }
    }
}