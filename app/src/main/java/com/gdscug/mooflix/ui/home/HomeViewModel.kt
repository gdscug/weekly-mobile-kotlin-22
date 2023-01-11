package com.gdscug.mooflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gdscug.mooflix.BuildConfig
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.local.MoviesEntity
import com.gdscug.mooflix.data.remote.response.MoviesResponse
import com.gdscug.mooflix.utils.DataDummy

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies() : LiveData<MoviesResponse> = movieRepository.getMovie(apiKey)

    companion object {
        private var apiKey = BuildConfig.apiKey
    }
}