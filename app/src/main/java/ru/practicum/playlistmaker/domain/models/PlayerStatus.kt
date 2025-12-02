package ru.practicum.playlistmaker.domain.models

data class PlayerStatus(
    val isPlaying: Boolean = false,
    val progress: Float = 0f
)