package com.practicum.playlistmaker

import ru.practicum.playlistmaker.creator.Storage
import ru.practicum.playlistmaker.data.network.RetrofitNetworkClient
import ru.practicum.playlistmaker.data.network.TracksRepositoryImpl
import ru.practicum.playlistmaker.domain.api.TracksRepository

object Creator {
//    fun getRepository(): TracksRepository {
//        return TracksRepositoryImpl(NetworkClientImpl())
//    }
//
//    fun provideTracksInteractor(): TracksInteractor {
//        return TracksInteractorImpl(getRepository())
//    }
    fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(RetrofitNetworkClient(Storage()))
    }
}