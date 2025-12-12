package ru.practicum.playlistmaker.domain.api

import ru.practicum.playlistmaker.domain.models.Track

interface TracksRepository {
    fun searchTracks(expression: String): List<Track>
    fun getTrackById(id: Int): Track
    suspend fun getAllTracks(): List<Track>
}