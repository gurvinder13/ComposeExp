package com.example.composeexp.repository

import com.example.composeexp.api.MovieApi
import com.example.composeexp.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {
    private val _movieList = MutableStateFlow<List<Result>>(emptyList())
    val movieList: StateFlow<List<Result>>
        get() = _movieList

    suspend fun getPopularMovie() {
        val response = movieApi.getPopularMovie("en-US", 1)
        if (response.isSuccessful && response.body() != null) {
         _movieList.emit(response.body()!!.results)
        }
    }
}