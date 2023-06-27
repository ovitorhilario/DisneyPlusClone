package com.vitorhilarioapps.disneyplus.ui.destinations.poster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.vitorhilarioapps.disneyplus.ui.theme.semiTransparent
import com.vitorhilarioapps.disneyplus.ui.utlis.ModernIcon

@Composable
fun PosterImage(
    url: String,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 56.dp, bottomEnd = 56.dp))
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Icon(
            modifier = Modifier
                .padding(16.dp)
                .background(semiTransparent, CircleShape)
                .clickable { onClose() }
                .padding(14.dp)
                .size(24.dp)
                .align(Alignment.TopEnd)
            ,
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = Color.White
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    onClick = { /*TODO*/ }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(imageVector = Icons.Rounded.PlayArrow, contentDescription = null)
                        Text(text = "Play", color = Color.Black)
                    }
                }

                ModernIcon(
                    tint = Color.White,
                    background = semiTransparent,
                    imageVector = Icons.Rounded.Add) { /*TODO*/ }
            }

            ModernIcon(
                tint = Color.White,
                background = semiTransparent,
                imageVector = Icons.Rounded.MoreVert) { /*TODO*/ }
        }
    }
}