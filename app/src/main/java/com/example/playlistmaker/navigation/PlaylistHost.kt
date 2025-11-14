package com.example.playlistmaker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playlistmaker.composables.screens.FavouriteScreen
import com.example.playlistmaker.composables.screens.MainScreen
import com.example.playlistmaker.composables.screens.PlaylistScreen
import com.example.playlistmaker.composables.screens.SearchScreen
import com.example.playlistmaker.composables.screens.SettingsScreen
import com.example.playlistmaker.data.SettingsContextManager
import com.example.playlistmaker.ui.theme.IsDarkTheme
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme
import com.example.playlistmaker.ui.theme.ThemeManager


@Composable
fun PlaylistHost() {
    val navController = rememberNavController()
    val currentTheme by ThemeManager.currentTheme.collectAsState()

    PlaylistMakerTheme(
        IsDarkTheme(currentTheme)
    ) {
        NavHost(navController = navController, startDestination = Screens.MAIN.name) {
            composable(Screens.MAIN.name) {
                MainScreen(
                    onNavigateToSearch = { navigateToSearch(navController) },
                    onNavigateToPlaylists = { navigateToPlaylists(navController) },
                    onNavigateToFavourite = { navigateToFavourites(navController) },
                    onNavigateToSettings = { navigateToSettings(navController) }
                )
            }

            composable(Screens.SEARCH.name) {
                SearchScreen(
                    onNavigateBack = { navigateBack(navController) }
                )
            }

            composable(Screens.PLAYLISTS.name) {
                PlaylistScreen(
                    onNavigateBack = { navigateBack(navController) }
                )
            }

            composable(Screens.FAVOURITES.name) {
                FavouriteScreen(
                    onNavigateBack = { navigateBack(navController) }
                )
            }

            composable(Screens.SETTINGS.name) {
                val context = LocalContext.current
                val settingsContextManager = SettingsContextManager(context)
                SettingsScreen(
                    onNavigateBack = { navigateBack(navController) },
                    settingsContextManager = settingsContextManager
                )
            }
        }
    }
}






