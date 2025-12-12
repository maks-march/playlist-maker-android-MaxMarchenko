package ru.practicum.playlistmaker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.playlistmaker.Creator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.playlistmaker.UI.interfaces.TrackPlayer
import ru.practicum.playlistmaker.domain.impl.TrackPlayerImpl
import ru.practicum.playlistmaker.domain.impl.TracksInteractor
import ru.practicum.playlistmaker.domain.models.PlayerStatus
import ru.practicum.playlistmaker.domain.models.TrackModel
import ru.practicum.playlistmaker.domain.models.TrackScreenState

class TrackViewModel(
    private var trackId: Int,
    private val tracksInteractor: TracksInteractor,
    private val trackPlayer: TrackPlayer,
) : ViewModel() {
    private val _trackScreenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Loading)
    val trackScreenState = _trackScreenState.asStateFlow()
    private val _playerStatusState = MutableStateFlow<PlayerStatus>(PlayerStatus())
    val playerStatusState = _playerStatusState.asStateFlow()

    init {
        loadTrackData(trackId);
    }

    fun loadTrackData(id: Int) {
        trackId = id
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
            statusObserver = object : TrackPlayer.StatusObserver {
                override fun onProgress(progress: Float) {
                    _playerStatusState.value = currentStatus.copy(progress = progress)
                }

                override fun onStop() {
                    _playerStatusState.value = currentStatus.copy(isPlaying = false)
                }

                override fun onPlay() {
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
        fun getViewModelFactory(trackId: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val interactor = Creator.provideTracksInteractor()
                val trackPlayer = TrackPlayerImpl()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer,
                )
            }
        }
    }
}