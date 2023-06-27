package com.vitorhilarioapps.disneyplus.ui.destinations.poster

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vitorhilarioapps.disneyplus.ui.MainViewModel
import com.vitorhilarioapps.disneyplus.ui.theme.CyanBlue
import org.koin.androidx.compose.koinViewModel

@Composable
fun PosterScreen(
    viewModel: MainViewModel = koinViewModel(),
    movieId: Int,
    onClose: () -> Unit
) {
    val BASE_URL = "https://image.tmdb.org/t/p/w500"
    viewModel.fetchMovie(movieId.toString())
    val currentMoviePoster by viewModel.currentMoviePoster.collectAsState()

    currentMoviePoster?.let { movie ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            PosterImage(url = BASE_URL + movie.poster_path, onClose = { onClose() })

            Spacer(modifier = Modifier.height(24.dp))

            PosterImdbInfo(
                year = movie.release_date.substring(0, 4),
                voteAverage = movie.vote_average.toString(),
                mediaType = String.format("%.2f", movie.popularity)
            )

            Spacer(modifier = Modifier.height(24.dp))

            PosterDescription(name = movie.title, overview = movie.overview)
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp).align(Alignment.Center),
                color = CyanBlue,
            )
        }
    }
}