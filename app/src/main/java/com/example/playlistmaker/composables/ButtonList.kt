package com.example.playlistmaker.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> ButtonsList(
    items: Set<T>,
    onClick: (T) -> Unit,
    content: @Composable (T) -> Unit,
) {
    for (item in items) {
        Box(
            Modifier.clickable {
                onClick(item)
            }
                .fillMaxWidth()
                .border(
                    width = 0.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            content(item)
        }
    }
}