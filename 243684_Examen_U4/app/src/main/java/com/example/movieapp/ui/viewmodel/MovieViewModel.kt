package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.SearchResult
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Initial : UiState()
    object Loading : UiState()
    data class Success(val movies: List<SearchResult>) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    val favorites: StateFlow<List<Movie>> = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        searchMovies("avengers")
    }

    fun searchMovies(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _uiState.value = UiState.Initial
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = repository.searchMovies(query)
                if (response.response == "True" && !response.search.isNullOrEmpty()) {
                    _uiState.value = UiState.Success(response.search)
                } else {
                    _uiState.value = UiState.Error("No se encontraron películas")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error en búsqueda")
            }
        }
    }

    fun loadMovieDetails(imdbId: String) {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieDetails(imdbId)
                _selectedMovie.value = movie
            } catch (e: Exception) {
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            if (repository.isFavorite(movie.imdbID)) {
                repository.removeFavorite(movie)
            } else {
                repository.addFavorite(movie)
            }
        }
    }

    fun removeFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.removeFavorite(movie)
        }
    }

    suspend fun isFavorite(imdbId: String): Boolean {
        return repository.isFavorite(imdbId)
    }
}