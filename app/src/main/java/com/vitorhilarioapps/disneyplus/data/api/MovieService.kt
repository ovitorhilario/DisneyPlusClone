package com.vitorhilarioapps.disneyplus.data.api

import com.vitorhilarioapps.disneyplus.data.model.MovieInfo
import com.vitorhilarioapps.disneyplus.data.model.MovieList
import com.vitorhilarioapps.disneyplus.data.model.QueryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("list/{list_id}")
    suspend fun getList(
        @Path("list_id") listId: String,
        @Query("api_key") apiKey: String
    ): MovieList

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") movie: String,
        @Query("api_key") apiKey: String
    ): QueryResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieInfo

}