package com.practicum.playlistmaker.data.dto

class TracksSearchResponse(val results: List<TrackDto>, resultCode: Int = 200) : BaseResponse(resultCode) {
}