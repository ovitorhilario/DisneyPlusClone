package com.vitorhilarioapps.disneyplus.ui.destinations.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.vitorhilarioapps.disneyplus.ui.theme.Gray
import com.vitorhilarioapps.disneyplus.ui.theme.Typography
import com.vitorhilarioapps.disneyplus.ui.theme.darkGray
import com.vitorhilarioapps.disneyplus.ui.theme.lightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier,
    toggleExpanded: () -> Unit,
    fetchByQuery: (String) -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = modifier
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            value = textState.value,
            textStyle = Typography.bodyLarge,
            onValueChange = {
                textState.value = it
                it.text.takeIf { text -> text.isNotEmpty() }?.let { value ->
                    fetchByQuery(value)
                }
            },
            singleLine = true,
            placeholder = {
                Text(text = "Movies, shows and more...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = lightGray
                )
            },
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = lightGray,
                containerColor = darkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Icon(
            modifier = Modifier
                .background(Gray, CircleShape)
                .padding(14.dp)
                .size(28.dp)
                .clickable { toggleExpanded() }
                .align(Alignment.CenterEnd),
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = lightGray
        )
    }
}