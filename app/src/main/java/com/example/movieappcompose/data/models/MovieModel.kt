package com.example.movieappcompose.data.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double = 0.0,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenreModel>? = listOf(),

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,
)