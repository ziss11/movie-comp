package com.example.movieappcompose.utilities

sealed class ResultState<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    object Initial : ResultState<Nothing>()
}
