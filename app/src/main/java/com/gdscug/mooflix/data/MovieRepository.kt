package com.gdscug.mooflix.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdscug.mooflix.data.remote.response.MoviesResponse
import com.gdscug.mooflix.data.remote.RemoteDataSource

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
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

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }
}