package ru.practicum.playlistmaker.UI.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.theme.Blue
import ru.practicum.playlistmaker.domain.models.TrackScreenState
import ru.practicum.playlistmaker.presentation.TrackViewModel

@Composable
fun TrackScreenContent(viewModel: TrackViewModel, screenState: TrackScreenState.Content) {
    val playerStatus by viewModel.playerStatusState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(R.drawable.ic_music),
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.weight(2f)
        )
        Text(
        screenState.trackModel.author,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.2f)
        )
        Text(
        screenState.trackModel.name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.2f)
        )

        val buttonIcon = if (playerStatus.isPlaying) {
            Icons.Default.Close
        } else {
            Icons.Default.PlayArrow
        }
        IconButton(
            {},
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .weight(0.2f),
        ) {
            Icon(
                imageVector = buttonIcon,
                null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
            )
        }

        Slider(
            value = playerStatus.progress,
            onValueChange = { newProgress ->
                viewModel.seek(newProgress)
            },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth(0.8f).weight(0.8f),
            colors = SliderColors(
                MaterialTheme.colorScheme.onPrimary,
                Blue,
                Blue,
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.tertiary,
            )
        )
    }
}
