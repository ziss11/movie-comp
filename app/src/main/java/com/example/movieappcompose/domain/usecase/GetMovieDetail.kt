package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetMovieDetail private constructor(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, apiKey: String) = repository.getMovieDetail(movieId, apiKey)

    companion object {
        private var instance: GetMovieDetail? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: GetMovieDetail(Injection.provideMovieRepository(context))
        }.also { instance = it }
    }
}