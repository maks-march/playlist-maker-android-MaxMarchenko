package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.UI.track.TrackPlayer
import com.practicum.playlistmaker.data.TracksRepositoryImpl
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.data.network.NetworkClientImpl
import com.practicum.playlistmaker.domain.impl.TrackPlayerImpl
import com.practicum.playlistmaker.domain.impl.TracksInteractor
import com.practicum.playlistmaker.domain.impl.TracksInteractorImpl

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