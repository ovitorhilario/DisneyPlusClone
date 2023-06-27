package com.vitorhilarioapps.disneyplus.ui.destinations.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.data.model.Movie
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@Composable
fun SimpleMediaRow(
    tittle: String,
    content: List<Movie>,
    positionInColumn: Int,
    openPoster: (Int) -> Unit
) {

    val modifier = if (positionInColumn == 3) {
        Modifier
            .width(160.dp)
            .height(256.dp)
    } else {
        Modifier
            .width(120.dp)
            .height(192.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp),
            text = tittle,
            style = Typography.bodyLarge,
            color = lightGray
        )

        LazyRow (
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){

            items(content) { item ->
                SimpleMedia(movie = item, modifier = modifier) { openPoster(it) }
            }
        }
    }
}

@Composable
fun SimpleMedia(
    movie: Movie,
    modifier: Modifier,
    openPoster: (Int) -> Unit
) {
    val BASE_URL = "https://image.tmdb.org/t/p/w500"

    Box (
        modifier = modifier
    ) {

        AsyncImage(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable { openPoster(movie.id) }
            ,
            model = BASE_URL + movie.poster_path,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}
