package ru.practicum.playlistmaker.domain.models

sealed class SearchState {
    object Initial: SearchState() // Cостояние экрана при первой загрузке
    object Searching: SearchState() // Cостояние экрана при начале поиска
    data class Success(val foundList: List<Track>): SearchState() // Cостояние экрана при успешном завершении поиска
    data class Fail(val error: String): SearchState() // Cостояние экрана если при запросе к серверу произошла ошибка
} 