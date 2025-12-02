package com.practicum.playlistmaker.UI.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.UI.composables.Screen

@Composable
fun PlaylistScreen(
    onNavigateBack: () -> Unit
) {
    Screen(
        stringResource(R.string.playlists),
        onNavigateBack = onNavigateBack
    ) {

    }
}