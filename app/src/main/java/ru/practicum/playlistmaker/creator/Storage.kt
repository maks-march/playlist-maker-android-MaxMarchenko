package ru.practicum.playlistmaker.creator

import ru.practicum.playlistmaker.data.dto.TrackDto

class Storage {
    private val listTracks = listOf(
        TrackDto(
            trackId = 0,
            trackName = "Владивосток 2000",
            artistName = "Мумий Троль",
            trackTimeMillis = 158000 // 2:38
        ),
        TrackDto(
            trackId = 1,
            trackName = "Группа крови",
            artistName = "Кино",
            trackTimeMillis = 283000 // 4:43
        ),
        TrackDto(
            trackId = 2,
            trackName = "Не смотри назад",
            artistName = "Ария",
            trackTimeMillis = 312000 // 5:12
        ),
        TrackDto(
            trackId = 3,
            trackName = "Звезда по имени Солнце",
            artistName = "Кино",
            trackTimeMillis = 225000
        ),
        TrackDto(
            trackId = 4,
            trackName = "Лондон",
            artistName = "Аквариум",
            trackTimeMillis = 272000
        ),
        TrackDto(
            trackId = 5,
            trackName = "На заре",
            artistName = "Альянс",
            trackTimeMillis = 230000
        ),
        TrackDto(
            trackId = 6,
            trackName = "Перемен",
            artistName = "Кино",
            trackTimeMillis = 296000
        ),
        TrackDto(
            trackId = 7,
            trackName = "Розовый фламинго",
            artistName = "Сплин",
            trackTimeMillis = 195000
        ),
        TrackDto(
            trackId = 8,
            trackName = "Танцевать",
            artistName = "Мельница",
            trackTimeMillis = 222000
        ),
        TrackDto(
            trackId = 9,
            trackName = "Чёрный бумер",
            artistName = "Серега",
            trackTimeMillis = 241000
        )
    )

    fun search(request: String): List<TrackDto> {
        val result = listTracks.filter {
            it.trackName
                .lowercase()
                .contains(request.lowercase()) ||
            it.artistName
                .lowercase()
                .contains(request.lowercase())
        }
        return result
    }

    fun get(index: Int): TrackDto {
        return listTracks[index]
    }

    fun getAll(): List<TrackDto> {
        return listTracks;
    }
}