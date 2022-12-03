package com.example.movieappcompose.data.models

import com.example.movieappcompose.domain.entities.Genre
import com.google.gson.annotations.SerializedName

data class GenreModel(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String? = null
) {
    fun toEntity() = Genre(
        id = id,
        name = name ?: "",
    )
}
