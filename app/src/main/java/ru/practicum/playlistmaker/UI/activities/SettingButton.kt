package ru.practicum.playlistmaker.UI.activities

import androidx.compose.runtime.Composable
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.ButtonIcon

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
