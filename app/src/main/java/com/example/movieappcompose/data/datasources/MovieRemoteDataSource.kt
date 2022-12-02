package com.example.movieappcompose.data.datasources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.movieappcompose.Injection.provideApiService
import com.example.movieappcompose.data.datasources.service.ApiService
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.utilities.ResultState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MovieRemoteDataSource {
    fun getPopularMovies(apiKey: String): ResultState<List<MovieModel>>
    fun getNowPlayingMovies(apiKey: String): ResultState<List<MovieModel>>
    fun getRecommendedMoviesById(movieId: Int, apiKey: String): ResultState<List<MovieModel>>
    fun getMovieDetail(movieId: Int, apiKey: String): ResultState<MovieModel>
    fun searchMovie(apiKey: String, query: String): ResultState<List<MovieModel>>
}

class MovieRemoteDataSourceImpl private constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {

    private var popularResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
    private var nowPlayingResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
    private var recommendedResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)
    private var detailResult: ResultState<MovieModel> by mutableStateOf(ResultState.Loading)
    private var searchResult: ResultState<List<MovieModel>> by mutableStateOf(ResultState.Loading)

    override fun getPopularMovies(apiKey: String): ResultState<List<MovieModel>> {
        popularResult = ResultState.Loading

        val client = apiService.getPopularMovies(apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    ResultState.Success(responseBody).also { popularResult = it }
                } else {
                    popularResult = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                popularResult = ResultState.Error(t.message.toString())
            }
        })

        return popularResult
    }

    override fun getNowPlayingMovies(apiKey: String): ResultState<List<MovieModel>> {
        nowPlayingResult = ResultState.Loading

        val client = apiService.getNowPlayingMovies(apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    ResultState.Success(responseBody).also { nowPlayingResult = it }
                } else {
                    nowPlayingResult = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                nowPlayingResult = ResultState.Error(t.message.toString())
            }
        })

        return nowPlayingResult
    }

    override fun getRecommendedMoviesById(
        movieId: Int,
        apiKey: String
    ): ResultState<List<MovieModel>> {
        recommendedResult = ResultState.Loading

        val client = apiService.getRecommendedMoviesById(movieId, apiKey)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    ResultState.Success(responseBody).also { recommendedResult = it }
                } else {
                    recommendedResult = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                recommendedResult = ResultState.Error(t.message.toString())
            }
        })

        return recommendedResult
    }

    override fun getMovieDetail(movieId: Int, apiKey: String): ResultState<MovieModel> {
        detailResult = ResultState.Loading

        val client = apiService.getMovieDetail(movieId, apiKey)
        client.enqueue(object : Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    ResultState.Success(responseBody).also { detailResult = it }
                } else {
                    detailResult = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                detailResult = ResultState.Error(t.message.toString())
            }
        })

        return detailResult
    }

    override fun searchMovie(
        apiKey: String,
        query: String
    ): ResultState<List<MovieModel>> {
        searchResult = ResultState.Loading

        val client = apiService.searchMovie(apiKey, query)
        client.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    ResultState.Success(responseBody).also { searchResult = it }
                } else {
                    searchResult = ResultState.Error(response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                searchResult = ResultState.Error(t.message.toString())
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