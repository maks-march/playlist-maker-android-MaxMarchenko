package ru.practicum.playlistmaker.domain.impl

import ru.practicum.playlistmaker.UI.activities.track.TrackPlayer

class TrackPlayerImpl: TrackPlayer {
    override fun play(
        trackId: String,
        statusObserver: TrackPlayer.StatusObserver
    ) {
        statusObserver.onPlay()

        for (progress in 0..100 step 10) {
            Thread.sleep(500)
            statusObserver.onProgress(progress / 100f)
        }

        statusObserver.onStop()
    }

    override fun pause(trackId: String) {
        TODO("Not yet implemented")
    }

    override fun seek(trackId: String, position: Float) {
        TODO("Not yet implemented")
    }

    override fun release(trackId: String) {
        TODO("Not yet implemented")
    }
}