package com.dgimenes.architecturesample.data

import com.dgimenes.architecturesample.data.datasource.MovieDAO
import com.dgimenes.architecturesample.data.model.Movie
import com.dgimenes.architecturesample.web.MovieWebService
import io.reactivex.Maybe
import io.reactivex.Single

open class MovieRepository(private val movieWebService: MovieWebService,
                      private val movieDAO: MovieDAO) {

    open fun getPopularMovies(): Single<List<Movie>> =
            getPopularMoviesFromLocal()
                    .switchIfEmpty(getPopularMoviesFromRemote())

    private fun getPopularMoviesFromRemote(): Single<List<Movie>> =
            movieWebService.getPopularMovies()
                    .map {
                        it.results.map { it.toModel() }
                    }
                    .doOnSuccess { movies ->
                        movieDAO.saveMovies(movies)
                    }

    private fun getPopularMoviesFromLocal(): Maybe<List<Movie>> =
            movieDAO.getMovies()
                    .filter { it.isNotEmpty() }

}