package ru.practicum.playlistmaker.UI.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.practicum.playlistmaker.data.ButtonElement

@Composable
fun ButtonIcon(item: ButtonElement) {
    Icon(
        painterResource(item.iconId),
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
    )
}