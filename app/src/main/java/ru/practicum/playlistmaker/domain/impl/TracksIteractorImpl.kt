package ru.practicum.playlistmaker.domain.impl

import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.domain.models.TrackModel

class TracksInteractorImpl(
    private val tracksRepository: TracksRepository
) : TracksInteractor  {
    override fun loadTrackData(trackId: Int, onComplete: (TrackModel) -> Unit) {
        var track = tracksRepository.getTrackById(trackId)
        val trackModel = TrackModel(
            id = trackId,
            name = track.trackName,
            author = track.artistName,
            pictureUrl = "https://example.com/cover.jpg"
        )
        onComplete(trackModel)
    }
}