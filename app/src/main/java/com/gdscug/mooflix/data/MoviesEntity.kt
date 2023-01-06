package com.gdscug.mooflix.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesEntity(
    val id : String?,
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val voteAverage: String?,
    val posterPath: String?
) : Parcelable
