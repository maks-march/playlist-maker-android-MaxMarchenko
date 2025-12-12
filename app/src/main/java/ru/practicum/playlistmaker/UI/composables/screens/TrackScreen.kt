package ru.practicum.playlistmaker.UI.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.practicum.playlistmaker.UI.composables.Screen
import ru.practicum.playlistmaker.UI.composables.TrackScreenContent
import ru.practicum.playlistmaker.domain.models.TrackScreenState
import ru.practicum.playlistmaker.presentation.TrackViewModel

@Composable
fun TrackScreen(
    viewModel: TrackViewModel,
    onNavigateBack: () -> Unit
) {
    val screenState by viewModel.trackScreenState.collectAsState()
    Screen(
        "",
        onNavigateBack = onNavigateBack
    ) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            when (screenState) {
                is TrackScreenState.Content -> {
                    TrackScreenContent(viewModel, screenState as TrackScreenState.Content) //1
                }
                is TrackScreenState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}