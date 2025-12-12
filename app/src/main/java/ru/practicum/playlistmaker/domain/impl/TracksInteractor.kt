package ru.practicum.playlistmaker.domain.impl

import ru.practicum.playlistmaker.domain.models.TrackModel

interface TracksInteractor {
    fun loadTrackData(trackId: Int, onComplete: (TrackModel) -> Unit)
}