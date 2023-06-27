package com.vitorhilarioapps.disneyplus.data.model

data class QueryResponse (
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)


