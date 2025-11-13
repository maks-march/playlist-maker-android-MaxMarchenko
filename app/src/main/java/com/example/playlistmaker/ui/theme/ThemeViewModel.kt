package com.example.playlistmaker.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    private val _currentTheme = MutableStateFlow(Theme.LIGHT)
    val currentTheme = _currentTheme.asStateFlow()
    fun changeTheme(theme: Theme) {
        _currentTheme.value = theme
    }
}

enum class Theme {
    LIGHT, DARK
}