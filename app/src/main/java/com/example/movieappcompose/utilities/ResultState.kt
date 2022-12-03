package com.example.movieappcompose.utilities

sealed interface ResultState {
    data class Success<out T>(val data: T) : ResultState
    object Error : ResultState
    object Loading : ResultState
    object Initial : ResultState
}
