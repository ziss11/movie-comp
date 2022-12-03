package com.example.movieappcompose.domain.entities

data class Movie(
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val genres: List<Genre>? = listOf(),
    val tagline: String? = null,
)