package com.example.movieappcompose.domain.usecase

import android.content.Context
import com.example.movieappcompose.Injection
import com.example.movieappcompose.domain.repositories.MovieRepository

class GetWatchlistStatus private constructor(private val repository: MovieRepository) {
    fun execute(id: Int) = repository.isWatchlist(id)

    companion object {
        private var instance: GetWatchlistStatus? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: GetWatchlistStatus(
                Injection.provideMovieRepository(context)
            )
        }.also { instance = it }
    }
}
