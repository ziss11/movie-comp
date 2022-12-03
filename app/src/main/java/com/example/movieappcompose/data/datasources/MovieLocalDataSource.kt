package com.example.movieappcompose.data.datasources

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieappcompose.Injection.provideMovieDao
import com.example.movieappcompose.data.datasources.database.MovieDao
import com.example.movieappcompose.data.models.MovieTable
import com.example.movieappcompose.utilities.ResultState

interface MovieLocalDataSource {
    suspend fun getWatchlistMovies(): List<MovieTable>
    suspend fun isWatchlist(id: Int): Boolean
    suspend fun addWatchlistMovie(movie: MovieTable)
    suspend fun removeWatchlistMovie(id: Int)
}

class MovieLocalDataSourceImpl private constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {
    override suspend fun getWatchlistMovies(): List<MovieTable> {
        return movieDao.getWatchlistMovies()
    }

    override suspend fun isWatchlist(id: Int): Boolean {
        return movieDao.isWatchlist(id)
    }

    override suspend fun addWatchlistMovie(movie: MovieTable) {
        movieDao.addMovie(movie)
    }

    override suspend fun removeWatchlistMovie(id: Int) {
        movieDao.removeMovie(id)
    }

    companion object {
        private var instance: MovieLocalDataSourceImpl? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MovieLocalDataSourceImpl(provideMovieDao(context))
        }.also { instance = it }
    }
}