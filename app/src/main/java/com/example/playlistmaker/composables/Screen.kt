package com.example.playlistmaker.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.playlistmaker.R
import com.example.playlistmaker.ui.theme.Typography

@Composable
fun Screen(
    title: String,
    isMain: Boolean = false,
    bodyContent: @Composable () -> Unit
) {
    val backgroundColor = if (isMain) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = backgroundColor
            )
            .padding(top = 32.dp)
    ) {
        Header(
            title,
            isMain
        )
        Body(bodyContent)
    }
}

@Composable
fun Body(
    content: @Composable () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primary,
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

