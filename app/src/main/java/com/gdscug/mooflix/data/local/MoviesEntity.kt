package com.gdscug.mooflix.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesEntity(
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("overview")
    val overview: String?,
    @field:SerializedName("release_date")
    val releaseDate: String?,
    @field:SerializedName("vote_average")
    val voteAverage: String?,
    @field:SerializedName("poster_path")
    val posterPath: String?
) : Parcelable
