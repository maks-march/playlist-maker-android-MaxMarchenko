package com.practicum.playlistmaker.domain.impl

import com.practicum.playlistmaker.domain.models.TrackModel

interface TracksInteractor {
    fun loadTrackData(trackId: String, onComplete: (TrackModel) -> Unit)
}