package com.example.movieappcompose.utilities

sealed class ResultState<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultState<T>()
    object Error : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    object Initial : ResultState<Nothing>()
}
