package com.example.movieappcompose.data.datasources

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieappcompose.Injection.provideMovieDao
import com.example.movieappcompose.data.datasources.database.MovieDao
import com.example.movieappcompose.data.models.MovieTable

interface MovieLocalDataSource {
    fun getWatchlistMovies(): LiveData<List<MovieTable>>
    fun isWatchlist(id: Int): LiveData<Boolean>
    suspend fun addWatchlistMovie(movie: MovieTable)
    suspend fun removeWatchlistMovie(id: Int)
}

class MovieLocalDataSourceImpl private constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {
    override fun getWatchlistMovies(): LiveData<List<MovieTable>> {
        return movieDao.getWatchlistMovies()
    }

    override fun isWatchlist(id: Int): LiveData<Boolean> = movieDao.isWatchlist(id)

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