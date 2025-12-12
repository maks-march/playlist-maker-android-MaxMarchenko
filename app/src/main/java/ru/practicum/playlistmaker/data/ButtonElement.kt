package ru.practicum.playlistmaker.data

import androidx.compose.runtime.Composable
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.ButtonIcon

data class ButtonElement(
    val iconId: Int = R.drawable.arrow_icon,
    val icon: @Composable (ButtonElement) -> Unit = { it -> ButtonIcon(it) },
    val nameId: Int,
    val task: () -> Unit = {})
