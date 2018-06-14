package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.Movie

class MovieListViewModel: ViewModel() {

    private val movies: List<Movie> = listOf(
            Movie(1, "Man Of Steel"),
            Movie(2, "Batman"),
            Movie(3, "Matrix"),
            Movie(4, "Jurassic Park"),
            Movie(4, "Ocean Eleven"),
            Movie(4, "007 Skyfall"),
            Movie(4, "Dead Pool 2"),
            Movie(4, "Forrest Gump")
    )

    fun init() {
    }

    fun getMovies(): List<Movie> {
        return movies
    }

}