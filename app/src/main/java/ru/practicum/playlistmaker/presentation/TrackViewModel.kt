package ru.practicum.playlistmaker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.practicum.playlistmaker.creator.MyApplication
import ru.practicum.playlistmaker.UI.activities.track.TrackPlayer
import ru.practicum.playlistmaker.domain.impl.TracksInteractor
import ru.practicum.playlistmaker.domain.models.PlayerStatus
import ru.practicum.playlistmaker.domain.models.TrackModel
import ru.practicum.playlistmaker.domain.models.TrackScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackViewModel(
    private val trackId: String,
    private val tracksInteractor: TracksInteractor,
    private val trackPlayer: TrackPlayer,
) : ViewModel() {
    private val _trackScreenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Loading)
    val trackScreenState = _trackScreenState.asStateFlow()
    private val _playerStatusState = MutableStateFlow<PlayerStatus>(PlayerStatus())
    val playerStatusState = _playerStatusState.asStateFlow()

    init {
        loadTrackData();
    }

    private fun loadTrackData() {
        tracksInteractor.loadTrackData(
            trackId = trackId,
            onComplete = { trackModel: TrackModel ->
                _trackScreenState.value = TrackScreenState.Content(trackModel)
            }
        )
    }

    fun play() {
        val currentStatus = _playerStatusState.value
        trackPlayer.play(
            trackId = trackId,
            // 1
            statusObserver = object : TrackPlayer.StatusObserver {
                override fun onProgress(progress: Float) {
                    // 2
                    _playerStatusState.value = currentStatus.copy(progress = progress)
                }

                override fun onStop() {
                    // 3
                    _playerStatusState.value = currentStatus.copy(isPlaying = false)
                }

                override fun onPlay() {
                    // 4
                    _playerStatusState.value = currentStatus.copy(isPlaying = true)
                }
            },
        )
    }

    // 5
    fun pause() {
        trackPlayer.pause(trackId)
    }

    fun seek(position: Float) {
        trackPlayer.seek(trackId, position)
    }

    // 6
    override fun onCleared() {
        trackPlayer.release(trackId)
    }


    companion object {
        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val interactor = myApp.provideTracksInteractor()
                val trackPlayer = myApp.provideTrackPlayer()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer,
                )
            }
        }
    }
}