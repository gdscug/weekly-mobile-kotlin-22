package com.gdscug.mooflix.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.di.Injection
import com.gdscug.mooflix.ui.home.HomeViewModel
import com.gdscug.mooflix.ui.login.LoginViewModel
import com.gdscug.mooflix.ui.register.RegisterViewModel

class ViewModelFactory(private val movieRepository: MovieRepository):
ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: $modelClass")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }
    }
}