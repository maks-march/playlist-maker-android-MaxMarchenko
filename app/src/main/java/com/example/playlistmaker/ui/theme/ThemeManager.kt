package com.example.playlistmaker.ui.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ThemeManager {
    private var _currentTheme = MutableStateFlow(Theme.LIGHT)
    var currentTheme = _currentTheme.asStateFlow()
    fun toggleTheme() {
        if (_currentTheme.value == Theme.LIGHT)
            _currentTheme.value = Theme.DARK
        else
            _currentTheme.value = Theme.LIGHT
    }
}

enum class Theme {
    LIGHT, DARK
}