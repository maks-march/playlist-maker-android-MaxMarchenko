package ru.practicum.playlistmaker.domain.impl

import ru.practicum.playlistmaker.domain.api.TrackSearchInteractor
import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.domain.models.Track

class TrackSearchInteractorImpl(private val repository: TracksRepository) : TrackSearchInteractor {

    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }

    override suspend fun getAllTracks(): List<Track> {
        return repository.getAllTracks()
    }
}