package com.gdscug.mooflix.networking

import com.gdscug.mooflix.data.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") apiKey: String): Call<MoviesResponse>
}