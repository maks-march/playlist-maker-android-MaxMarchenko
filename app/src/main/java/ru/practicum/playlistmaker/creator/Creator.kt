package com.practicum.playlistmaker

import ru.practicum.playlistmaker.creator.Storage
import ru.practicum.playlistmaker.data.network.NetworkClientImpl
import ru.practicum.playlistmaker.data.network.TracksRepositoryImpl
import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.domain.impl.TrackPlayerImpl
import ru.practicum.playlistmaker.domain.impl.TracksInteractor
import ru.practicum.playlistmaker.domain.impl.TracksInteractorImpl
import ru.practicum.playlistmaker.presentation.TrackViewModel

object Creator {
    fun provideTracksInteractor(): TracksInteractor {
        return TracksInteractorImpl(getTracksRepository())
    }
    fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(NetworkClientImpl(Storage()))
    }

    fun getTrackViewModel(trackId: Int): TrackViewModel {

        return TrackViewModel(
            trackId = trackId,
            tracksInteractor = provideTracksInteractor(),
            trackPlayer = TrackPlayerImpl()
        )
    }
}