package com.gdscug.mooflix.data

import androidx.lifecycle.LiveData
import com.gdscug.mooflix.data.remote.response.MoviesResponse

interface MovieDataSource {

    fun getMovie(apiKey: String): LiveData<MoviesResponse>
}