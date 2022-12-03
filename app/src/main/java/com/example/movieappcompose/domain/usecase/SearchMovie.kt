package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class SearchMovie private constructor(private val repository: MovieRepository) {
    fun execute(apiKey: String, query: String) = repository.searchMovie(apiKey, query)

    companion object {
        private var instance: SearchMovie? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: SearchMovie(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}