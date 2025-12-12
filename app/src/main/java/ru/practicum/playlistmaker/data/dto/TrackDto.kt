package ru.practicum.playlistmaker.data.dto

data class TrackDto(
    val trackId: Int,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
)