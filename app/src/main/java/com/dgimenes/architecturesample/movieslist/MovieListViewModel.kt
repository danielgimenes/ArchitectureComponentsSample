package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.data.model.Movie
import io.reactivex.Observable

class MovieListViewModel : ViewModel() {

    private lateinit var movieRepository: MovieRepository

    fun init() {
        movieRepository = createMovieRepository()
    }

    fun loadPopularMovies(): Observable<List<Movie>> =
            movieRepository.getPopularMovies()

}

fun createMovieRepository(): MovieRepository {
    return MovieRepository()
}