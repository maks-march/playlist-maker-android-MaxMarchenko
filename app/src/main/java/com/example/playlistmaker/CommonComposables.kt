package com.example.playlistmaker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import data.boldTextStyle

@Composable
fun CommonMainScreen(
    title: String,
    content: @Composable () -> Unit = { }
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.main_blue)
            )
            .padding(top = 32.dp)
    ) {
        Box (
            modifier = Modifier
                .padding(16.dp, 18.dp)
        ) {
            Text(
                title,
                color = Color.White,
                style = boldTextStyle
            )
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .padding(16.dp, 8.dp)
        ) {
            content()
        }
    }
}

@Composable
fun <T> CommonButtonsList(
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