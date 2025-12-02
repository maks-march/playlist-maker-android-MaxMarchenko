package com.practicum.playlistmaker.data

import androidx.compose.runtime.Composable
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.UI.composables.ButtonIcon

class SettingButton(
    val iconId: Int = R.drawable.arrow_icon,
    val icon: @Composable (SettingButton) -> Unit = { it -> ButtonIcon(it) },
    val nameId: Int,
    val task: () -> Unit = {})
{
    @Composable
    fun Icon() {
        icon(this)
    }
}
