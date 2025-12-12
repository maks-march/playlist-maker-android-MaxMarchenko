package ru.practicum.playlistmaker.data.network

import ru.practicum.playlistmaker.creator.Storage
import ru.practicum.playlistmaker.data.dto.TracksGetRequest
import ru.practicum.playlistmaker.domain.api.NetworkClient
import ru.practicum.playlistmaker.data.dto.TracksSearchRequest
import ru.practicum.playlistmaker.data.dto.TracksResponse

class NetworkClientImpl(private val storage: Storage) : NetworkClient {

    override fun doRequest(request: Any): TracksResponse {
        when (request) {
            is TracksSearchRequest -> {
                if (request.expression == "") {
                    var resultList = storage.getAll()
                    return TracksResponse(resultList).apply { resultCode = 200 }
                }

                val resultList = storage.search(request.expression)
                if (resultList.size == 0)
                    return TracksResponse(resultList).apply { resultCode = 404 }
                return TracksResponse(resultList).apply { resultCode = 200 }
            }
            is TracksGetRequest -> {
                var resultList = listOf(storage.get(request.id))
                if (resultList.size == 0)
                    return TracksResponse(resultList).apply { resultCode = 404 }
                return TracksResponse(resultList).apply { resultCode = 200 }
            }
            else -> {
                return TracksResponse(emptyList())
            }
        }


    }
}