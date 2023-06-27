package com.vitorhilarioapps.disneyplus.ui.destinations.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vitorhilarioapps.disneyplus.ui.MainViewModel
import com.vitorhilarioapps.disneyplus.ui.destinations.landing.ContentLanding
import com.vitorhilarioapps.disneyplus.ui.destinations.landing.SheetContent
import com.vitorhilarioapps.disneyplus.ui.destinations.login.LoginData
import com.vitorhilarioapps.disneyplus.ui.theme.darkGray
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel = koinViewModel(),
    profileId: Int,
    openPoster: (Int) -> Unit
) {

    val movies by viewModel.movies.collectAsState()
    val queryMovies by viewModel.queryMovies.collectAsState()

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var avatarId by remember { mutableStateOf(profileId) }

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetBackgroundColor = darkGray,
        sheetState = state,
        sheetContent = {
            SheetContent(
                avatarList = LoginData.avatarContent,
                onClose = { scope.launch { state.hide() } },
                onChangeProfileId = { id -> avatarId = id }
            )
        }
    ) {
        // content landing page
        ContentLanding(
            profileId = avatarId,
            uiState = movies,
            queryUiState = queryMovies,
            onOpenModal = { scope.launch { state.show() } },
            openPoster = { scope.launch { openPoster(it) } },
            fetchByQuery = { scope.launch { viewModel.fetchByQuery(it) } },
            onClearQuery = { scope.launch { viewModel.clearQuery() }},
        )
    }
}
