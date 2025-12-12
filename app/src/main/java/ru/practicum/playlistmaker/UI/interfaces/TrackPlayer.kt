package ru.practicum.playlistmaker.UI.interfaces

interface TrackPlayer {
    // 1
    fun play(trackId: Int, statusObserver: StatusObserver)
    fun pause(trackId: Int)
    fun seek(trackId: Int, position: Float)
    fun release(trackId: Int)

    // 2
    interface StatusObserver {
        fun onProgress(progress: Float)
        fun onStop()
        fun onPlay()
    }
}