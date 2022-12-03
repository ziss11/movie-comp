package com.example.movieappcompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieappcompose.domain.entities.Movie

@Entity(tableName = "movie")
data class MovieTable(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String? = null,
) {
    constructor(movie: Movie): this(movie.id, movie.title, movie.overview)

    fun toEntity() = Movie(
        id = id,
        title = title,
        overview = overview ?: "",
    )
}
