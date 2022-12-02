package com.example.movieappcompose.data.datasources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.movieappcompose.Injection.provideApiService
import com.example.movieappcompose.data.datasources.service.ApiService
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MovieRemoteDataSource {
    fun getPopularMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>>
    fun getNowPlayingMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>>
    fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ): LiveData<ResultState<List<MovieModel>>>

    fun getMovieDetail(
        movieId: Int,
        apiKey: String
    ): LiveData<ResultState<MovieModel>>

    fun searchMovie(
        apiKey: String,
        query: String
    ): LiveData<ResultState<List<MovieModel>>>
}

class MovieRemoteDataSourceImpl private constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {

    private val popularResult = MediatorLiveData<ResultState<List<MovieModel>>>()
    private val nowPlayingResult = MediatorLiveData<ResultState<List<MovieModel>>>()
    private val recommendedResult = MediatorLiveData<ResultState<List<MovieModel>>>()
    private val detailResult = MediatorLiveData<ResultState<MovieModel>>()
    private val searchResult = MediatorLiveData<ResultState<List<MovieModel>>>()

    override fun getPopularMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>> {
        popularResult.value = ResultState.Loading

        val client = apiService.getPopularMovies(apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    popularResult.value = ResultState.Success(responseBody)
                } else {
                    popularResult.value = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                popularResult.value = ResultState.Error(t.message.toString())
            }
        })

        return popularResult
    }

    override fun getNowPlayingMovies(apiKey: String): LiveData<ResultState<List<MovieModel>>> {
        nowPlayingResult.value = ResultState.Loading

        val client = apiService.getNowPlayingMovies(apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    nowPlayingResult.value = ResultState.Success(responseBody)
                } else {
                    nowPlayingResult.value = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                nowPlayingResult.value = ResultState.Error(t.message.toString())
            }
        })

        return nowPlayingResult
    }

    override fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ): LiveData<ResultState<List<MovieModel>>> {
        recommendedResult.value = ResultState.Loading

        val client = apiService.getRecommendedMoviesById(movieId, apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    recommendedResult.value = ResultState.Success(responseBody)
                } else {
                    recommendedResult.value = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                recommendedResult.value = ResultState.Error(t.message.toString())
            }
        })

        return recommendedResult
    }

    override fun getMovieDetail(movieId: Int, apiKey: String): LiveData<ResultState<MovieModel>> {
        detailResult.value = ResultState.Loading

        val client = apiService.getMovieDetail(movieId, apiKey)
        client.enqueue(object : Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    detailResult.value = ResultState.Success(responseBody)
                } else {
                    detailResult.value = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                detailResult.value = ResultState.Error(t.message.toString())
            }
        })

        return detailResult
    }

    override fun searchMovie(
        apiKey: String,
        query: String
    ): LiveData<ResultState<List<MovieModel>>> {
        searchResult.value = ResultState.Loading

        val client = apiService.searchMovie(apiKey, query)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    searchResult.value = ResultState.Success(responseBody)
                } else {
                    searchResult.value = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                searchResult.value = ResultState.Error(t.message.toString())
            }
        })

        return searchResult
    }

    companion object {
        private var instance: MovieRemoteDataSourceImpl? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MovieRemoteDataSourceImpl(provideApiService())
        }.also { instance = it }
    }
}