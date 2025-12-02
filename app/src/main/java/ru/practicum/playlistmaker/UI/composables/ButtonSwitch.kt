package ru.practicum.playlistmaker.UI.composables

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import ru.practicum.playlistmaker.UI.theme.*

@Composable
fun ThemeSwitch() {
    val currentTheme by ThemeManager.currentTheme.collectAsState()
    ButtonSwitch(
        isChecked = IsDarkTheme(currentTheme),
        onCheckedChange = { isChecked ->
            ThemeManager.toggleTheme()
        }
    )
}

@Composable
fun ButtonSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Blue,
            checkedTrackColor = LightBlue,
            uncheckedThumbColor = LightGray,
            uncheckedTrackColor = Gray,
            uncheckedBorderColor = Color.Transparent,
            checkedBorderColor = Color.Transparent
        )
    )
}