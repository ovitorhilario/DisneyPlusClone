package com.vitorhilarioapps.disneyplus.data.api

import com.vitorhilarioapps.disneyplus.data.model.MovieInfo
import com.vitorhilarioapps.disneyplus.data.model.MovieList
import com.vitorhilarioapps.disneyplus.data.model.QueryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieApiImpl(private val service: MovieService) : MovieApi {

    override fun fetchList(listId: String, apiKey: String): Flow<MovieList> = flow {
        emit(service.getList(listId, apiKey))
    }

    override fun fetchByQuery(movie: String, apiKey: String): Flow<QueryResponse> = flow {
        emit(service.searchMovie(movie, apiKey))
    }

    override fun fetchMovie(movieId: String, apiKey: String): Flow<MovieInfo> = flow {
        emit(service.getMovie(movieId, apiKey))
    }
}