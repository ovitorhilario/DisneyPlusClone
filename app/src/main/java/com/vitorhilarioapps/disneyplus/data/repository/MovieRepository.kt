package com.vitorhilarioapps.disneyplus.data.repository

import com.vitorhilarioapps.disneyplus.data.model.MovieInfo
import com.vitorhilarioapps.disneyplus.data.model.MovieList
import com.vitorhilarioapps.disneyplus.data.model.QueryResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchList(listId: String, apiKey: String): Flow<MovieList>
    fun fetchByQuery(movie: String, apiKey: String): Flow<QueryResponse>
    fun fetchMovie(movieId: String, apiKey: String): Flow<MovieInfo>
}