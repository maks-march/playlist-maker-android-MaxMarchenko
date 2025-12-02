package ru.practicum.playlistmaker.UI.activities.track

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.practicum.playlistmaker.UI.composables.screens.AllTracksScreen
import ru.practicum.playlistmaker.presentation.SearchViewModel
import ru.practicum.playlistmaker.UI.theme.*
import kotlin.getValue

class AllTracksActivity : ComponentActivity() {
    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModel.getViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme by ThemeManager.currentTheme.collectAsState()
            PlaylistMakerTheme(
                IsDarkTheme(currentTheme)
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllTracksScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}