package com.vitorhilarioapps.disneyplus.ui.utlis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ModernIcon(
    tint: Color,
    background: Color,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .background(background, CircleShape)
            .clickable { onClick() }
            .padding(14.dp)
            .size(24.dp)
        ,
        imageVector = imageVector,
        contentDescription = null,
        tint = tint
    )
}