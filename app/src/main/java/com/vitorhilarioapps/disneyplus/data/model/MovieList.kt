package com.vitorhilarioapps.disneyplus.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    val created_by: String,
    val description: String,
    val favorite_count: Int,
    val id: String,
    val iso_639_1: String,
    val item_count: Int,
    @SerializedName("items")
    val movies: List<Movie>,
    val name: String,
    val poster_path: Any
)