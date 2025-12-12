package ru.practicum.playlistmaker.domain.models

data class Track(
    val trackId: Int,
    val trackName: String,
    val artistName: String,
    val trackTime: String
)