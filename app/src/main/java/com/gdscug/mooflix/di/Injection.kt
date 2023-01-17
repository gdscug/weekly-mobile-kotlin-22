package com.gdscug.mooflix.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.remote.RemoteDataSource

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("movie")

object Injection {

    fun provideRepository(context: Context): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource, context.dataStore)
    }
}