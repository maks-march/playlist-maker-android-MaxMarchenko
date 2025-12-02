package ru.practicum.playlistmaker.domain.models

sealed class TrackScreenState {
    object Loading: TrackScreenState()
    data class Content(
        val trackModel: TrackModel,
    ): TrackScreenState()
} 