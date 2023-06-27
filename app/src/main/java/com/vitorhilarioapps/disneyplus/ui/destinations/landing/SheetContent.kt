package com.vitorhilarioapps.disneyplus.ui.destinations.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorhilarioapps.disneyplus.ui.destinations.login.LoginData
import com.vitorhilarioapps.disneyplus.ui.theme.Gray
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@Composable
fun SheetContent(
    avatarList: List<LoginData.Profile>,
    onClose: () -> Unit,
    onChangeProfileId: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 48.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Who's your mascot?",
                style = Typography.titleLarge,
                color = Color.White
            )

            Icon(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .background(Gray, CircleShape)
                    .padding(4.dp)
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onClose() }
                ,
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                tint = lightGray
            )
        }

        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                Spacer(modifier = Modifier.width(112.dp))
            }

            items(avatarList) {
                AsyncImage(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .clickable { onChangeProfileId(it.id) },
                    model = it.model,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}