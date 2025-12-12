package ru.practicum.playlistmaker.UI.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.practicum.playlistmaker.Creator
import ru.practicum.playlistmaker.UI.composables.screens.FavouriteScreen
import ru.practicum.playlistmaker.UI.composables.screens.MainScreen
import ru.practicum.playlistmaker.UI.composables.screens.PlaylistScreen
import ru.practicum.playlistmaker.UI.composables.screens.SearchScreen
import ru.practicum.playlistmaker.UI.composables.screens.SettingsScreen
import ru.practicum.playlistmaker.UI.composables.screens.TrackScreen
import ru.practicum.playlistmaker.presentation.SearchViewModel
import ru.practicum.playlistmaker.presentation.SettingsContextManager


@Composable
fun PlaylistHost(
    searchViewModel: SearchViewModel
) {
    val navController = rememberNavController()

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
                searchViewModel,
                onNavigateToTrack = { trackId ->
                    navigateToTrack(navController, trackId)
                },
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

        composable(
            "${Screens.TRACK.name}/{trackId}",
            arguments = listOf(
                navArgument("trackId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val trackId = backStackEntry.arguments?.getInt("trackId") ?: -1
            val trackViewModel = Creator.getTrackViewModel(trackId);
            TrackScreen(
                trackViewModel,
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






