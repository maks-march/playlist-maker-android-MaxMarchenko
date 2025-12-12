package ru.practicum.playlistmaker.UI.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.domain.models.Track

@Composable
fun TrackListItem(
    track: Track,
    onNavigateToTrack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(60.dp)
            .clickable {
                onNavigateToTrack()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier.weight(0.5f),
            Alignment.Center
        ) {
            Icon(
                modifier = Modifier.width(30.dp),
                painter = painterResource(id = R.drawable.ic_music),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Трек ${track.trackName}"
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                track.trackName,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                track.artistName,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                track.trackTime,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium)
        }
        Box(
            Modifier.weight(0.5f),
            Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.arrow_icon),
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = null
            )
        }
    }
}