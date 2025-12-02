package com.practicum.playlistmaker.data

import com.practicum.playlistmaker.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}