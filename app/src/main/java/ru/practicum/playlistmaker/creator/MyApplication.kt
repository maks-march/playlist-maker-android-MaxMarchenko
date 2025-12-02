package ru.practicum.playlistmaker.creator

import android.app.Application
import ru.practicum.playlistmaker.UI.activities.track.TrackPlayer
import ru.practicum.playlistmaker.data.network.TracksRepositoryImpl
import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.data.network.NetworkClientImpl
import ru.practicum.playlistmaker.domain.impl.TrackPlayerImpl
import ru.practicum.playlistmaker.domain.impl.TracksInteractor
import ru.practicum.playlistmaker.domain.impl.TracksInteractorImpl

class MyApplication: Application() {
    fun getRepository(): TracksRepository {
        return TracksRepositoryImpl(NetworkClientImpl())
    }

    fun provideTracksInteractor(): TracksInteractor {
        return TracksInteractorImpl(getRepository())
    }

    fun provideTrackPlayer() : TrackPlayer {
        return TrackPlayerImpl();
    }
}