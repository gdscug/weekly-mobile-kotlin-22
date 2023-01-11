package com.gdscug.mooflix.data.remote

import android.util.Log
import com.gdscug.mooflix.data.remote.response.MoviesResponse
import com.gdscug.mooflix.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovie(apiKey: String, callback: LoadMovieCallback) {
        ApiConfig.instance.getMovie(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.allMovieReceived(it) }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })

    }

    interface LoadMovieCallback {
        fun allMovieReceived(moviesResponse: MoviesResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private const val TAG = "RemoteData"
    }
}