package com.practicum.playlistmaker.UI.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practicum.playlistmaker.theme.Blue

@Composable
fun Screen(
    title: String,
    isMain: Boolean = false,
    onNavigateBack: () -> Unit = {},
    bodyContent: @Composable () -> Unit
) {
    val backgroundColor = if (isMain) Blue else MaterialTheme.colorScheme.primary
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
            isMain,
            onNavigateBack
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

