package com.practicum.playlistmaker.data.network

import com.practicum.playlistmaker.data.NetworkClient
import com.practicum.playlistmaker.data.dto.BaseResponse
import com.practicum.playlistmaker.data.dto.TrackDto
import com.practicum.playlistmaker.data.dto.TracksSearchRequest
import com.practicum.playlistmaker.data.dto.TracksSearchResponse

class NetworkClientImpl : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return when (dto) {
            is TracksSearchRequest -> {
                TracksSearchResponse(
                    results = listOf(
                        TrackDto(
                            trackName = "Bohemian Rhapsody",
                            artistName = "Queen",
                            trackTimeMillis = 354000 // 5:54
                        ),
                        TrackDto(
                            trackName = "Hotel California",
                            artistName = "Eagles",
                            trackTimeMillis = 391000 // 6:31
                        ),
                        TrackDto(
                            trackName = "Stairway to Heaven",
                            artistName = "Led Zeppelin",
                            trackTimeMillis = 482000 // 8:02
                        )
                    ),
                    resultCode = 200
                )
            }
            else -> {
                // Для других запросов возвращаем базовый ответ с ошибкой
                BaseResponse(resultCode = 404)
            }
        }
    }
}