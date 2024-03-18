package com.example.composeexp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexp.model.MovieListResponse
import com.example.composeexp.model.Result
import com.example.composeexp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private var movieRepository: MovieRepository) :
    ViewModel() {

    val movieList: StateFlow<List<Result>>
        get() = movieRepository.movieList

    init {
        viewModelScope.launch {
            movieRepository.getPopularMovie()
        }
    }
}