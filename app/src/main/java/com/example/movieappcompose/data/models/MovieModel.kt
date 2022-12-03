package com.example.movieappcompose.data.models

import com.example.movieappcompose.domain.entities.Movie
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenreModel>? = listOf(),

    @field:SerializedName("tagline")
    val tagline: String? = null,
) {
    fun toEntity() = Movie(
        id = id,
        title = title ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        backdropPath = backdropPath ?: "",
        genres = genres?.map { it.toEntity() } ?: listOf(),
        tagline = tagline ?: "",
    )
}