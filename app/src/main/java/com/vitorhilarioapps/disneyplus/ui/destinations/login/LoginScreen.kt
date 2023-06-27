package com.vitorhilarioapps.disneyplus.ui.destinations.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.ui.theme.CyanBlue
import com.vitorhilarioapps.disneyplus.ui.theme.Typography

@Composable
fun LoginScreen(
    onLoginToHome: (Int) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 48.dp, bottom = 48.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.titleLarge,
            text = "Who's watching?",
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 48.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(LoginData.avatarContent) { item ->
                Avatar(item) { id -> onLoginToHome(id) }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp),
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                shape = CircleShape,
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 32.dp),
                text = "Edit",
                style = Typography.bodyLarge,
                color = CyanBlue
            )
        }
    }
}

@Composable
fun Avatar(
    profile: LoginData.Profile,
    onLoginToHome: (Int) -> Unit
){
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .clickable { onLoginToHome(profile.id) },
            model = profile.model,
            contentDescription = null
        )

        Text(
            text = profile.name,
            color= Color.Gray,
            style = Typography.bodyLarge
        )
    }
}

