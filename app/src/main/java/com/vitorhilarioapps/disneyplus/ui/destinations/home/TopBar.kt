package com.vitorhilarioapps.disneyplus.ui.destinations.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vitorhilarioapps.disneyplus.ui.theme.Gray
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@Composable
fun TopBar(
    toggleExpanded: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Everything",
            color = Color.White,
            style = Typography.headlineLarge
        )
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = null,
            tint = lightGray
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            modifier = Modifier
                .background(Gray, CircleShape)
                .padding(14.dp)
                .size(28.dp)
                .clickable { toggleExpanded() },
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = lightGray
        )

        Icon(
            modifier = Modifier
                .background(Gray, CircleShape)
                .padding(14.dp)
                .size(28.dp),
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            tint = lightGray
        )
    }
}