package com.sjcoding.networksdk.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjcoding.networksdk.data.model.Movie
import com.sjcoding.networksdk.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    private val _latestMovies = MutableStateFlow<List<Movie>>(emptyList())
    val latestMovies: StateFlow<List<Movie>> get() = _latestMovies
    init {
        fetchPopularMovies()
        fetchLatestMovies()
    }



    private fun fetchPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = moviesRepository.fetchPopularMovies()
        }
    }

    private fun fetchLatestMovies() {
        viewModelScope.launch {
            _latestMovies.value = moviesRepository.fetchLatestMovies()
        }
    }
}
