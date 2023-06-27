package com.vitorhilarioapps.disneyplus.ui.destinations.poster

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@Composable
fun PosterImdbInfo(
    year: String,
    voteAverage: String,
    mediaType: String
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = buildString {
                append(year, " • ", mediaType.lowercase().replaceFirstChar { it.uppercase() })
            },
            color = lightGray,
            style = Typography.bodyLarge
        )

        // Imdb Image
        AsyncImage(
            modifier = Modifier.height(24.dp),
            model = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/IMDB_Logo_2016.svg/2560px-IMDB_Logo_2016.svg.png",
            contentDescription = null
        )

        Text(
            text = voteAverage,
            color = lightGray,
            style = Typography.bodyLarge
        )
    }
}