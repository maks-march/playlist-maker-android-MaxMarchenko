package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.playlistmaker.composables.ButtonsList
import com.example.playlistmaker.composables.Screen
import com.example.playlistmaker.data.NavigationButton
import com.example.playlistmaker.ui.theme.IsDarkTheme
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme
import com.example.playlistmaker.ui.theme.Theme
import com.example.playlistmaker.ui.theme.ThemeManager
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme by ThemeManager.currentTheme.collectAsState()

            PlaylistMakerTheme(
                IsDarkTheme(currentTheme)
            ) {
                Screen(
                    title = stringResource(R.string.app_name),
                    isMain = true
                ) {
                    ButtonsList(
                        navigationItems,
                        onClick = { item -> startActivity(item.link()) }
                    ) { item ->
                        NavigationInfo(item)
                    }
                }
            }
        }
    }

    private val navigationItems = setOf(
        NavigationButton(
            iconId = R.drawable.search_icon,
            nameId = R.string.search,
            link = { Intent(this, SearchActivity::class.java) }
        ),
        NavigationButton(
            iconId = R.drawable.playlist_icon,
            nameId = R.string.playlists,
            link = { Intent(this, PlaylistsActivity::class.java) }
        ),
        NavigationButton(
            iconId = R.drawable.heart_icon,
            nameId = R.string.favourite,
            link = { Intent(this, FavouritesActivity::class.java) }
        ),
        NavigationButton(
            iconId = R.drawable.settings_icon,
            nameId = R.string.settings,
            link = { Intent(this, SettingsActivity::class.java) }
        )
    )

    @Composable
    private fun NavigationInfo(info: NavigationButton) {
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
}