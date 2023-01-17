package com.gdscug.mooflix.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun saveUser(user: User) {
        viewModelScope.launch {
            movieRepository.saveUser(user)
        }
    }
}