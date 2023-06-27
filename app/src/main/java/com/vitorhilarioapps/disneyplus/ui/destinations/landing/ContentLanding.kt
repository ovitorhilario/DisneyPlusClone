package com.vitorhilarioapps.disneyplus.ui.destinations.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.ui.destinations.home.AppBar
import com.vitorhilarioapps.disneyplus.ui.destinations.home.UiState
import com.vitorhilarioapps.disneyplus.ui.destinations.login.LoginData
import com.vitorhilarioapps.disneyplus.ui.theme.CyanBlue
import com.vitorhilarioapps.disneyplus.ui.utlis.MovieHelper

@Composable
fun ContentLanding(
    profileId: Int,
    uiState: UiState,
    queryUiState: UiState,
    onOpenModal: () -> Unit,
    openPoster: (Int) -> Unit,
    fetchByQuery: (String) -> Unit,
    onClearQuery: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),

        ) {

        MainContent(
            uiState = uiState,
            queryUiState = queryUiState,
            openPoster = { openPoster(it) },
            fetchByQuery = { fetchByQuery(it) },
            onClearQuery = { onClearQuery() },
        )

        // User Image
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .clip(CircleShape)
                .clickable { onOpenModal() },
            model = LoginData.avatarContent.firstOrNull { it.id == profileId }?.model,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContent(
    queryUiState: UiState,
    uiState: UiState,
    openPoster: (Int) -> Unit,
    fetchByQuery: (String) -> Unit,
    onClearQuery: () -> Unit,
) {

    val allMovies = uiState.movies.chunked(4)
    val queryMovies = queryUiState.movies

    val lazyColumnState = rememberLazyListState()
    val isSearchExpanded = remember { mutableStateOf(false) }

    fun toggleIsExpanded() {
        isSearchExpanded.value = !isSearchExpanded.value
    }

    LazyColumn(
        modifier = Modifier,
        state = lazyColumnState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        item {
            // TopBar and SearchBar
            AppBar(
                onSearchExpanded = { toggleIsExpanded() },
                fetchByQuery = { fetchByQuery(it) },
                onClearQuery =  { onClearQuery() },
                isSearchExpanded = isSearchExpanded.value
            )
        }

        if (!isSearchExpanded.value) {
            if (allMovies.isNotEmpty()) {
                items(allMovies.withIndex().map { Pair(it.index, it.value) }) { (pos, movies) ->
                    SimpleMediaRow(
                        tittle = if (pos <= (MovieHelper.LABEL_ROW.size - 1)) MovieHelper.LABEL_ROW[pos] else "Movies",
                        content = movies,
                        positionInColumn = pos,
                        openPoster = { openPoster(it) }
                    )
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(128.dp))

                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = CyanBlue,
                    )
                }
            }
        } else {
            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    maxItemsInEachRow = 3
                ) {
                    queryMovies.forEach { movie ->
                        AsyncImage(
                            model = MovieHelper.BASE_URL + movie.poster_path,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .height(160.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { openPoster(movie.id) }
                        )
                    }
                }
            }
        }
    }
}

