package ru.practicum.playlistmaker.UI.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.Screen

@Composable
fun FavouriteScreen(
    onNavigateBack: () -> Unit
) {
    Screen(
        stringResource(R.string.favourite),
        onNavigateBack = onNavigateBack
    ) {

    }
}