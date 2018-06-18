package com.dgimenes.architecturesample.web

import com.dgimenes.architecturesample.data.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieWebService {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = MOVIEDB_API_KEY): Single<PopularMoviesDTO>
}

data class PopularMoviesDTO(
        val results: List<MovieDTO>
)

data class MovieDTO(
        val id: Int,
        val title: String
) {
    fun toModel() = Movie(id = id, title = title)
}
