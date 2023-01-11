package com.gdscug.mooflix.data.remote.response

import com.gdscug.mooflix.data.local.MoviesEntity
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @field:SerializedName("results")
    val results: ArrayList<MoviesEntity>
)
