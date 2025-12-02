package ru.practicum.playlistmaker.domain.api

import ru.practicum.playlistmaker.domain.models.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<Track>
    suspend fun getAllTracks():List<Track>
} 