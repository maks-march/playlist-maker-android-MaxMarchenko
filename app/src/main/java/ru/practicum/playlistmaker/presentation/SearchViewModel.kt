package ru.practicum.playlistmaker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.practicum.playlistmaker.creator.MyApplication
import ru.practicum.playlistmaker.domain.api.TracksRepository
import ru.practicum.playlistmaker.domain.models.SearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(
    private val tracksRepository: TracksRepository,
) : ViewModel() {

    //...
    private val _allTracksScreenState = MutableStateFlow<SearchState>(SearchState.Loading)
    val allTracksScreenState = _allTracksScreenState.asStateFlow()

    fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allTracksScreenState.update { SearchState.Loading }
                val list = tracksRepository.getAllTracks()
                _allTracksScreenState.update { SearchState.Success(foundList = list) }
            } catch (e: IOException){
                _allTracksScreenState.update { SearchState.Error(e.message.toString()) }
            }
        }
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val repository = myApp.getRepository()

                SearchViewModel(
                    repository
                )
            }
        }
    }
}