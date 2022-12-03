package com.example.movieappcompose.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<Genre>,
    val tagline: String,
)