package com.example.movieappcompose.utilities

sealed interface ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>
    object Error : ResultState<Nothing>
    object Loading : ResultState<Nothing>
}
