package com.gdscug.mooflix.di

import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}