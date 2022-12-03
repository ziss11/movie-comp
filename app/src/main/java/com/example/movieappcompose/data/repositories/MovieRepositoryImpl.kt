package com.example.movieappcompose.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.movieappcompose.Injection.provideMovieLocalDataSource
import com.example.movieappcompose.Injection.provideMovieRemoteDataSource
import com.example.movieappcompose.data.datasources.MovieLocalDataSource
import com.example.movieappcompose.data.datasources.MovieRemoteDataSource
import com.example.movieappcompose.data.models.MovieResponse
import com.example.movieappcompose.data.models.MovieTable
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.domain.repositories.MovieRepository
import com.example.movieappcompose.utilities.ResultState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    private val nowPlayingRatedResult = MediatorLiveData<ResultState<List<Movie>>>()
    private val topRatedResult = MediatorLiveData<ResultState<List<Movie>>>()

    override fun getTopRatedMovies(apiKey: String): LiveData<ResultState<List<Movie>>> {
        val client = remoteDataSource.getTopRatedMovies(apiKey)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    topRatedResult.value =
                        ResultState.Success(responseBody.results?.map { it.toEntity() } ?: listOf())
                } else {
                    Log.d(TAG, response.message().toString())
                    topRatedResult.value = ResultState.Error
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                topRatedResult.value = ResultState.Error
            }
        })

        return topRatedResult
    }

    override fun getNowPlayingMovies(apiKey: String): LiveData<ResultState<List<Movie>>> {
        val client = remoteDataSource.getNowPlayingMovies(apiKey)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    nowPlayingRatedResult.value =
                        ResultState.Success(responseBody.results?.map { it.toEntity() } ?: listOf())
                } else {
                    Log.d(TAG, response.message().toString())
                    nowPlayingRatedResult.value = ResultState.Error
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                nowPlayingRatedResult.value = ResultState.Error
            }
        })

        return nowPlayingRatedResult
    }

    override suspend fun getRecommendedMoviesById(
        movieId: Int, apiKey: String
    ) = remoteDataSource.getRecommendedMoviesById(movieId, apiKey).map { it.toEntity() }

    override suspend fun getMovieDetail(movieId: Int, apiKey: String) =
        remoteDataSource.getMovieDetail(movieId, apiKey).toEntity()

    override suspend fun searchMovie(
        apiKey: String, query: String
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
        private val TAG = MovieRepositoryImpl::class.simpleName
        private var instance: MovieRepositoryImpl? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MovieRepositoryImpl(
                provideMovieRemoteDataSource(), provideMovieLocalDataSource(context)
            )
        }.also { instance = it }
    }
}