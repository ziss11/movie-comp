package com.example.movieappcompose.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<MovieModel>? = null
)
