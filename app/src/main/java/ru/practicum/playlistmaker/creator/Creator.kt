//package com.practicum.playlistmaker
//
//import com.practicum.playlistmaker.data.TracksRepositoryImpl
//import com.practicum.playlistmaker.domain.api.TracksRepository
//
//object Creator {
//    fun getRepository(): TracksRepository {
//        return TracksRepositoryImpl(NetworkClientImpl())
//    }
//
//    fun provideTracksInteractor(): TracksInteractor {
//        return TracksInteractorImpl(getRepository())
//    }
//}