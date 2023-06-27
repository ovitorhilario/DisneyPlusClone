package com.vitorhilarioapps.disneyplus.ui.destinations.poster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.darkGray
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@Composable
fun PosterDescription(
    name: String,
    overview: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(darkGray, RoundedCornerShape(topStart = 56.dp, topEnd = 56.dp))
            .padding(32.dp),
    ) {

        Text(
            text = name,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )

        Text(
            text = overview,
            color = lightGray,
            style = Typography.bodyLarge,
        )
    }
}