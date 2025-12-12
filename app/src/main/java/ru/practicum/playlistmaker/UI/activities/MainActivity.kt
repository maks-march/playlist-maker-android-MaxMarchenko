package ru.practicum.playlistmaker.UI.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.practicum.playlistmaker.UI.navigation.PlaylistHost
import ru.practicum.playlistmaker.UI.theme.IsDarkTheme
import ru.practicum.playlistmaker.UI.theme.PlaylistMakerTheme
import ru.practicum.playlistmaker.UI.theme.ThemeManager
import ru.practicum.playlistmaker.presentation.SearchViewModel
import ru.practicum.playlistmaker.presentation.TrackViewModel

class MainActivity : ComponentActivity() {
    private val searchViewModel by viewModels<SearchViewModel>{
        SearchViewModel.Companion.getViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme by ThemeManager.currentTheme.collectAsState()
            PlaylistMakerTheme(
                IsDarkTheme(currentTheme)
            ) {
                PlaylistHost(
                    searchViewModel
                )
            }
        }
    }
}