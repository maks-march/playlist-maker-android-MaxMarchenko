package com.example.playlistmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.playlistmaker.composables.Screen
import com.example.playlistmaker.ui.theme.IsDarkTheme
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme
import com.example.playlistmaker.ui.theme.ThemeManager

class FavouritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme by ThemeManager.currentTheme.collectAsState()

            PlaylistMakerTheme(
                IsDarkTheme(currentTheme)
            ) {
                Screen(
                    stringResource(R.string.favourite)
                ) {

                }
            }

        }
    }
}