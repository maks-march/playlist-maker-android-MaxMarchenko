package com.example.playlistmaker.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.playlistmaker.R
import com.example.playlistmaker.composables.Screen

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