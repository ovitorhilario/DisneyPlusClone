package com.vitorhilarioapps.disneyplus.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorhilarioapps.disneyplus.data.model.Movie
import com.vitorhilarioapps.disneyplus.data.model.MovieInfo
import com.vitorhilarioapps.disneyplus.data.repository.MovieRepository
import com.vitorhilarioapps.disneyplus.ui.destinations.home.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow(UiState())
    val movies get() = _movies

    private val _queryMovies = MutableStateFlow(UiState())
    val queryMovies get() = _queryMovies

    private val _currentMoviePoster = MutableStateFlow<MovieInfo?>(null)
    val currentMoviePoster get() = _currentMoviePoster

    init {
        fetchInitialMovies()
    }

    fun clearQuery() {
        _queryMovies.value = UiState()
    }

    fun fetchInitialMovies() {

        viewModelScope.launch {
            repository.fetchList(LIST_ID, API_KEY)
                .catch {
                    Log.e(TAG, "Error: ${it.message}")
                }
                .collect { movieResponse ->
                    _movies.value = UiState(
                        isLoading = false,
                        movies = movieResponse.movies.filter { it.media_type.equals("movie", true) }
                    )
                }
        }
    }

    fun fetchByQuery(name: String) {

        viewModelScope.launch {
            repository.fetchByQuery(name, API_KEY)
                .catch {
                    Log.e(TAG, "Error: ${it.message}")
                }
                .collect { queryResult ->
                    _queryMovies.value = UiState(
                        isLoading = false,
                        movies = queryResult.results.filter {
                            it.poster_path != null && (it.release_date?.length ?: 0) >= 4
                        }
                    )
                }
        }
    }

    fun fetchMovie(movieId: String) {

        viewModelScope.launch {
            repository.fetchMovie(movieId, API_KEY)
                .catch {
                    Log.e(TAG, "Error: ${it.message}")
                }
                .collect { queryResult ->
                    _currentMoviePoster.value = queryResult
                }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
        const val API_KEY = "YOUR_API_KEY_HERE"
        const val LIST_ID = "8258091"
    }
}