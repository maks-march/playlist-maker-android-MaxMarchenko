package com.example.playlistmaker.composables.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.playlistmaker.R
import com.example.playlistmaker.composables.ButtonsList
import com.example.playlistmaker.composables.Screen
import com.example.playlistmaker.data.NavigationInfo
import com.example.playlistmaker.navigation.Screens

@Composable
fun MainScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToPlaylists: () -> Unit,
    onNavigateToFavourite: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val navigationItems = setOf(
        NavigationInfo(
            iconId = R.drawable.search_icon,
            nameId = R.string.search,
            screen = Screens.SEARCH
        ),
        NavigationInfo(
            iconId = R.drawable.playlist_icon,
            nameId = R.string.playlists,
            screen = Screens.PLAYLISTS
        ),
        NavigationInfo(
            iconId = R.drawable.heart_icon,
            nameId = R.string.favourite,
            screen = Screens.FAVOURITES
        ),
        NavigationInfo(
            iconId = R.drawable.settings_icon,
            nameId = R.string.settings,
            screen = Screens.SETTINGS
        )
    )

    Screen(
        title = stringResource(R.string.app_name),
        isMain = true,
        onNavigateBack = {}
    ) {
        ButtonsList(
            navigationItems,
            onClick = { item ->
                when (item.screen) {
                    Screens.SEARCH -> onNavigateToSearch()
                    Screens.PLAYLISTS -> onNavigateToPlaylists()
                    Screens.FAVOURITES -> onNavigateToFavourite()
                    Screens.SETTINGS -> onNavigateToSettings()
                    else -> {}
                }
            }
        ) { item ->
            NavigationInfo(item)
        }
    }
}


@Composable
private fun NavigationInfo(info: NavigationInfo) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(info.iconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.weight(0.25f)
        )
        Text (
            stringResource(info.nameId),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Icon(
            painterResource(R.drawable.arrow_icon),
            tint = MaterialTheme.colorScheme.tertiary,
            contentDescription = null
        )
    }
}