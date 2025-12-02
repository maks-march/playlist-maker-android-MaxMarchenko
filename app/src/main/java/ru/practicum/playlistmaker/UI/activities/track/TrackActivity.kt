package ru.practicum.playlistmaker.UI.activities.track

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import ru.practicum.playlistmaker.domain.models.TrackScreenState
import ru.practicum.playlistmaker.presentation.TrackViewModel

class TrackActivity : ComponentActivity() {
    private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory("123") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackScreen(viewModel)
        }
    }
}

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    val screenState by viewModel.trackScreenState.collectAsState()

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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TrackScreenContent(viewModel: TrackViewModel, screenState: TrackScreenState.Content) {
    val playerStatus by viewModel.playerStatusState.collectAsState() //2

    Column {
        AsyncImage(
            model = screenState.trackModel.pictureUrl,
            contentDescription = null
        )
        Text(screenState.trackModel.author)
        Text(screenState.trackModel.name)

        val buttonIcon = if (playerStatus.isPlaying) {
            Icons.Default.Close
        } else {
            Icons.Default.PlayArrow
        }
        Button(
            content = { Image(buttonIcon, null) },
            onClick = {
            if (playerStatus.isPlaying) {
                viewModel.pause()
            } else {
                viewModel.play()
            }
        })

        Slider(
            value = playerStatus.progress,
            onValueChange = { newProgress ->
                viewModel.seek(newProgress)
            },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}
