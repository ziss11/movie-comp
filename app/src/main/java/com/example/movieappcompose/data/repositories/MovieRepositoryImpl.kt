package com.example.movieappcompose.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.movieappcompose.Injection.provideMovieLocalDataSource
import com.example.movieappcompose.Injection.provideMovieRemoteDataSource
import com.example.movieappcompose.data.datasources.MovieLocalDataSource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.data.models.MovieTable
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.repositories.MovieRepository
import com.example.movieappcompose.utilities.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override suspend fun getTopRatedMovies(apiKey: String) =
        remoteDataSource.getTopRatedMovies(apiKey).map { it.toEntity() }

    override suspend fun getNowPlayingMovies(apiKey: String) =
        remoteDataSource.getNowPlayingMovies(apiKey).map { it.toEntity() }

    override suspend fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ) = remoteDataSource.getRecommendedMoviesById(movieId, apiKey).map { it.toEntity() }

    override suspend fun getMovieDetail(movieId: Int, apiKey: String) =
        remoteDataSource.getMovieDetail(movieId, apiKey).toEntity()

    override suspend fun searchMovie(
        apiKey: String,
        query: String
    ) = remoteDataSource.searchMovie(apiKey, query).map { it.toEntity() }

    override fun getWatchlistMovies() = liveData {
        try {
            val response = localDataSource.getWatchlistMovies()
            val movieList: LiveData<ResultState<List<Movie>>> = response.map { list ->
                ResultState.Success(list.map { it.toEntity() })
            }
            emitSource(movieList)
        } catch (e: Exception) {
            emit(ResultState.Error)
        }
    }

    override fun isWatchlist(id: Int) = liveData {
        emitSource(localDataSource.isWatchlist(id))
    }

    override suspend fun addWatchlistMovie(movie: Movie) {
        localDataSource.addWatchlistMovie(MovieTable(movie = movie))
    }

    override suspend fun removeWatchlistMovie(id: Int) {
        localDataSource.removeWatchlistMovie(id)
    }

    companion object {
        private var instance: MovieRepositoryImpl? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MovieRepositoryImpl(
                provideMovieRemoteDataSource(),
                provideMovieLocalDataSource(context)
            )
        }.also { instance = it }
    }
}