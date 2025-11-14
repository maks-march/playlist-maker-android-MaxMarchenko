package com.example.playlistmaker.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.playlistmaker.data.SettingButton

@Composable
fun ButtonIcon(item: SettingButton) {
    Icon(
        painterResource(item.iconId),
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
    )
}