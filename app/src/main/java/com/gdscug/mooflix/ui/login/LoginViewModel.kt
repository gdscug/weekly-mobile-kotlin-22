package com.gdscug.mooflix.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gdscug.mooflix.data.MovieRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getUser() = movieRepository.getUser().asLiveData()

    fun login() {
        viewModelScope.launch {
            movieRepository.login()
        }
    }
}