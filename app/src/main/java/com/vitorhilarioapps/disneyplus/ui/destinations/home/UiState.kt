package com.vitorhilarioapps.disneyplus.ui.destinations.home

import com.vitorhilarioapps.disneyplus.data.model.Movie

data class UiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList()
)