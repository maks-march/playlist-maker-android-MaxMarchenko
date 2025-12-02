package com.practicum.playlistmaker.UI.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.theme.White

@Composable
fun Header(
    title: String,
    isMain: Boolean = false,
    onNavigateBack: () -> Unit
) {
    if (isMain) {
        MainHeader(title)
    } else {
        SecondaryHeader(
            title,
            onNavigateBack
        )
    }
}

@Composable
fun MainHeader(title: String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text (
            title,
            style = MaterialTheme.typography.titleLarge,
            color = White,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
    }
}

@Composable
fun SecondaryHeader(
    title: String,
    onNavigateBack: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onNavigateBack() }
        ) {
            Icon(
                painterResource(R.drawable.arrow_backwards),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text (
            title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
    }
}