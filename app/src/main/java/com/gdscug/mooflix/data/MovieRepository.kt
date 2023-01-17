package com.gdscug.mooflix.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdscug.mooflix.data.model.User
import com.gdscug.mooflix.data.remote.response.MoviesResponse
import com.gdscug.mooflix.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStore<Preferences>
) : MovieDataSource {

    override fun getMovie(apiKey: String): LiveData<MoviesResponse> {
        val movies = MutableLiveData<MoviesResponse>()
        remoteDataSource.getMovie(apiKey, object : RemoteDataSource.LoadMovieCallback {
            override fun allMovieReceived(moviesResponse: MoviesResponse) {
                movies.postValue(moviesResponse)
            }
        })

        return movies
    }

    fun getUser(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[nameKey] ?: "",
                preferences[emailKey] ?: "",
                preferences[passwordKey] ?: "",
                preferences[stateKey] ?: false,
            )
        }
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[nameKey] = user.name
            preferences[emailKey] = user.email
            preferences[passwordKey] = user.password
            preferences[stateKey] = user.isLogin
        }
    }

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[stateKey] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[stateKey] = false
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        private val nameKey = stringPreferencesKey("name")
        private val emailKey = stringPreferencesKey("email")
        private val passwordKey = stringPreferencesKey("password")
        private val stateKey = booleanPreferencesKey("isLogin")

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            dataStore: DataStore<Preferences>
        )
                : MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, dataStore)
            }
    }
}