package ru.practicum.playlistmaker.domain.api

import ru.practicum.playlistmaker.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}