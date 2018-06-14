package com.dgimenes.architecturesample.web

import com.dgimenes.architecturesample.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesWebService {

    @GET("/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = MOVIEDB_API_KEY): Call<PopularMoviesDTO>
}

data class PopularMoviesDTO(
        val results: List<MovieDTO>
)

data class MovieDTO(
        val id: Int,
        val originalTitle: String
) {
    fun toModel() = Movie(id = id, title = originalTitle)
}
