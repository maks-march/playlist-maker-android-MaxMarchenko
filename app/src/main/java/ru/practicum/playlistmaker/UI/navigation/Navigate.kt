package ru.practicum.playlistmaker.UI.navigation

import androidx.navigation.NavController

fun navigateToMain(navController: NavController) {
    navController.navigate(Screens.MAIN.name) {
        popUpTo(Screens.MAIN.name) { inclusive = true }
    }
}

fun navigateToSearch(navController: NavController) {
    navController.navigate(Screens.SEARCH.name)
}

fun navigateToPlaylists(navController: NavController) {
    navController.navigate(Screens.PLAYLISTS.name)
}

fun navigateToFavourites(navController: NavController) {
    navController.navigate(Screens.FAVOURITES.name)
}

fun navigateToSettings(navController: NavController) {
    navController.navigate(Screens.SETTINGS.name)
}

fun navigateBack(navController: NavController) {
    navController.popBackStack()
}

fun navigateToTrack(navController: NavController, trackId: Int) {
    navController.navigate("${Screens.TRACK.name}/$trackId")
}

fun navigateWithClearStack(
    navController: NavController,
    destination: Screens
) {
    navController.navigate(destination.name) {
        popUpTo(0) // Очищает весь стек
    }
}