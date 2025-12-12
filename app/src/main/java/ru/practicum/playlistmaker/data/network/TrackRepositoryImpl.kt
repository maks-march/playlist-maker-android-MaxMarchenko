package ru.practicum.playlistmaker.data.network

import kotlinx.coroutines.delay
import ru.practicum.playlistmaker.data.dto.BaseResponse
import ru.practicum.playlistmaker.data.dto.TracksGetRequest
import ru.practicum.playlistmaker.data.dto.TracksSearchRequest
import ru.practicum.playlistmaker.data.dto.TracksResponse
import ru.practicum.playlistmaker.domain.api.NetworkClient
import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.domain.models.Track

class TracksRepositoryImpl(private val networkClient: NetworkClient) : TracksRepository {

    override fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        return ProcessResponse(response)
    }

    override fun getTrackById(id: Int): Track {
        val response = networkClient.doRequest(TracksGetRequest(id))
        return ProcessResponse(response)[0]
    }

    override suspend fun getAllTracks(): List<Track> {
        delay(2000)
        val response = networkClient.doRequest(TracksSearchRequest(""))
        return ProcessResponse(response)
    }

    private fun ProcessResponse(response: BaseResponse): List<Track> {
        if (response.resultCode == 200) { // успешный запрос
            return (response as TracksResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(it.trackId, it.trackName, it.artistName, trackTime) }
        }
        if (response.resultCode == 404) {
            throw Exception("Не найдено!")
        }
        return emptyList()
    }
}