package ru.practicum.playlistmaker.data.network

import ru.practicum.playlistmaker.creator.Storage
import ru.practicum.playlistmaker.domain.api.NetworkClient
import ru.practicum.playlistmaker.data.dto.TracksSearchRequest
import ru.practicum.playlistmaker.data.dto.TracksSearchResponse

class RetrofitNetworkClient(private val storage: Storage) : NetworkClient {

    override fun doRequest(request: Any): TracksSearchResponse {
        val searchList = storage.search((request as TracksSearchRequest).expression)
        return TracksSearchResponse(searchList).apply { resultCode = 200 }
    }
}