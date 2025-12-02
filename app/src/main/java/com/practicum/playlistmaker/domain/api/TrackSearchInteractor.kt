package com.practicum.playlistmaker.domain.api

import com.practicum.playlistmaker.domain.models.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<Track>
    suspend fun getAllTracks():List<Track>
} 