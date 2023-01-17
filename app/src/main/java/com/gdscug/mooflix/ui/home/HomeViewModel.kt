package com.gdscug.mooflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gdscug.mooflix.BuildConfig
import com.gdscug.mooflix.BuildConfig.apiKey
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.remote.response.MoviesResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies() : LiveData<MoviesResponse> = movieRepository.getMovie(apiKey)

    fun getUser() = movieRepository.getUser().asLiveData()

    fun logout() {
        viewModelScope.launch {
            movieRepository.logout()
        }
    }

    companion object {
        private const val apiKey = BuildConfig.apiKey
    }
}