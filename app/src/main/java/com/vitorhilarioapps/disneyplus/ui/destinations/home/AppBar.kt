package com.vitorhilarioapps.disneyplus.ui.destinations.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.ui.destinations.search.Search
import com.vitorhilarioapps.disneyplus.ui.utlis.MovieHelper

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppBar(
    onSearchExpanded: () -> Unit,
    fetchByQuery: (String) -> Unit,
    onClearQuery: () -> Unit,
    isSearchExpanded: Boolean
) {

    val state = remember { MutableTransitionState(false).apply { targetState = true } }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visibleState = state,
            enter = scaleIn(),
            exit = scaleOut(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .size(120.dp),
                model = MovieHelper.DISNEY_LOGO,
                contentDescription = null
            ) 
        }

        AnimatedContent(targetState = isSearchExpanded, label = "") { expanded ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (!expanded) {
                    TopBar { onSearchExpanded() }
                } else {
                    Search(
                        modifier = Modifier
                           .fillMaxWidth()
                           .animateEnterExit(
                               enter = slideInHorizontally(),
                               exit = slideOutHorizontally()
                           ),
                        toggleExpanded = {
                            onSearchExpanded()
                            onClearQuery()
                         },
                        fetchByQuery = { fetchByQuery(it) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier
            .height(16.dp)
            .fillMaxWidth()
        )
    }
}