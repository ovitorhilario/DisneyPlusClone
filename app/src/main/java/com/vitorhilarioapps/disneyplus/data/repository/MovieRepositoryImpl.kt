package com.vitorhilarioapps.disneyplus.data.repository

import com.vitorhilarioapps.disneyplus.data.api.MovieApi
import com.vitorhilarioapps.disneyplus.data.model.MovieInfo
import com.vitorhilarioapps.disneyplus.data.model.MovieList
import com.vitorhilarioapps.disneyplus.data.model.QueryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val api: MovieApi
) : MovieRepository {

    override fun fetchList(listId: String, apiKey: String): Flow<MovieList> {
        return api.fetchList(listId, apiKey).flowOn(Dispatchers.IO)
    }

    override fun fetchByQuery(movie: String, apiKey: String): Flow<QueryResponse> {
        return api.fetchByQuery(movie, apiKey).flowOn(Dispatchers.IO)
    }

    override fun fetchMovie(movieId: String, apiKey: String): Flow<MovieInfo> {
        return api.fetchMovie(movieId, apiKey).flowOn(Dispatchers.IO)
    }
}