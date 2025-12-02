package ru.practicum.playlistmaker.UI.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.practicum.playlistmaker.UI.composables.screens.FavouriteScreen
import ru.practicum.playlistmaker.UI.composables.screens.MainScreen
import ru.practicum.playlistmaker.UI.composables.screens.PlaylistScreen
import ru.practicum.playlistmaker.UI.composables.screens.SearchScreen
import ru.practicum.playlistmaker.UI.composables.screens.SettingsScreen
import ru.practicum.playlistmaker.UI.activities.SettingsContextManager
import ru.practicum.playlistmaker.UI.theme.IsDarkTheme
import ru.practicum.playlistmaker.UI.theme.PlaylistMakerTheme
import ru.practicum.playlistmaker.UI.theme.ThemeManager


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






