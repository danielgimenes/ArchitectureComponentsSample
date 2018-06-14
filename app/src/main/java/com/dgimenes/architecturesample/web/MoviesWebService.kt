package com.dgimenes.architecturesample.web

import com.dgimenes.architecturesample.data.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesWebService {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = MOVIEDB_API_KEY): Observable<PopularMoviesDTO>
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
