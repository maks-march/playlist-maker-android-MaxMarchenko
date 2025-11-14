package com.example.playlistmaker.composables

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.playlistmaker.ui.theme.Blue
import com.example.playlistmaker.ui.theme.Gray
import com.example.playlistmaker.ui.theme.IsDarkTheme
import com.example.playlistmaker.ui.theme.LightBlue
import com.example.playlistmaker.ui.theme.LightGray
import com.example.playlistmaker.ui.theme.ThemeManager

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