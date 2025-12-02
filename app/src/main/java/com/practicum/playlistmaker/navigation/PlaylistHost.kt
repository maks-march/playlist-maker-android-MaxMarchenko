package com.practicum.playlistmaker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlistmaker.UI.composables.screens.FavouriteScreen
import com.practicum.playlistmaker.UI.composables.screens.MainScreen
import com.practicum.playlistmaker.UI.composables.screens.PlaylistScreen
import com.practicum.playlistmaker.UI.composables.screens.SearchScreen
import com.practicum.playlistmaker.UI.composables.screens.SettingsScreen
import com.practicum.playlistmaker.data.SettingsContextManager
import com.practicum.playlistmaker.theme.IsDarkTheme
import com.practicum.playlistmaker.theme.PlaylistMakerTheme
import com.practicum.playlistmaker.theme.ThemeManager


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






