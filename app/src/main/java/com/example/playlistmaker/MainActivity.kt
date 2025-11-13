package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.playlistmaker.ui.theme.ThemeViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerTheme(
                IsDarkTheme(viewModel)
            ) {
                Screen(
                    title = stringResource(R.string.app_name),
                    isMain = true
                ) {
                    ButtonsList(
                        navigationItems,
                        onClick = { item -> intentActivity(item) },
                        content = { item -> NavigationInfo(item) }
                    )
                }
            }
        }
    }

    val navigationItems = setOf(
        NavigationButton(
            iconId = R.drawable.search_icon,
            nameId = R.string.search,
            link = { Intent(this, SearchActivity::class.java) }
        ),
        NavigationButton(
            iconId = R.drawable.playlist_icon,
            nameId = R.string.playlists
        ),
        NavigationButton(
            iconId = R.drawable.heart_icon,
            nameId = R.string.favourite
        ),
        NavigationButton(
            iconId = R.drawable.settings_icon,
            nameId = R.string.settings,
            link = { Intent(this, SettingsActivity::class.java) }
        )
    )

    private fun intentActivity(info: NavigationButton) {
        val intent = info.link()
        intent?.let {
            startActivity(intent)
        } ?: run {
            Toast.makeText(
                    this@MainActivity,
            info.nameId,
            Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    fun NavigationInfo(info: NavigationButton) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 10.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(info.iconId),
                contentDescription = null,
                modifier = Modifier.weight(0.25f)
            )
            Text (
                stringResource(info.nameId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(2f)
            )
            Image(
                painterResource(R.drawable.arrow_icon),
                contentDescription = null
            )
        }
    }
}