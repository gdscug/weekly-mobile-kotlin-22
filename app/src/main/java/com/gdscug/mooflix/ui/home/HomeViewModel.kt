package com.gdscug.mooflix.ui.home

import androidx.lifecycle.ViewModel
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.utils.DataDummy

class HomeViewModel : ViewModel() {
    fun getMovies() : List<MoviesEntity> = DataDummy.generateDataDummy()
}