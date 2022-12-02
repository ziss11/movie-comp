package com.example.movieappcompose.data.models

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String? = null
)
