package ru.practicum.playlistmaker.domain.impl

import ru.practicum.playlistmaker.domain.models.TrackModel

interface TracksInteractor {
    fun loadTrackData(trackId: String, onComplete: (TrackModel) -> Unit)
}