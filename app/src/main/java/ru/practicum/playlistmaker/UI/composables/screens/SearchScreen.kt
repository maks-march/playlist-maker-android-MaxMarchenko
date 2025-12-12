package ru.practicum.playlistmaker.UI.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.ItemsList
import ru.practicum.playlistmaker.UI.composables.Screen
import ru.practicum.playlistmaker.UI.composables.SearchTextField
import ru.practicum.playlistmaker.UI.composables.TrackListItem
import ru.practicum.playlistmaker.domain.models.SearchState
import ru.practicum.playlistmaker.presentation.SearchViewModel


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateToTrack: (Int) -> Unit,
    onNavigateBack: () -> Unit
) {
    viewModel.clear()
    val screenState by viewModel.searchScreenState.collectAsState()
    var text by remember { mutableStateOf("") }
    Screen(
        stringResource(R.string.search),
        false,
        onNavigateBack
    ) {
        SearchTextField(
            viewModel,
            text
        ) {
            text = it
        }
        when (screenState) {
            is SearchState.Initial -> {
                Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.TopStart) {
                    Text(
                        stringResource(R.string.search_hint),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            is SearchState.Searching -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {
                val tracks = (screenState as SearchState.Success).foundList
                ItemsList(tracks) { track ->
                    TrackListItem(
                        track,
                        { onNavigateToTrack(track.trackId) }
                    )
                    HorizontalDivider(thickness = 0.5.dp)
                }
            }

            is SearchState.Fail -> {
                val error = (screenState as SearchState.Fail).error
                Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.TopStart) {
                    Text(
                        stringResource(R.string.error)+": $error",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }
            }
        }
    }
}