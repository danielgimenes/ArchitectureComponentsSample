package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.Movie
import com.dgimenes.architecturesample.web.MOVIEDB_BASEURL
import com.dgimenes.architecturesample.web.MoviesWebService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListViewModel : ViewModel() {

    private lateinit var moviesWebService: MoviesWebService

    fun init() {
        moviesWebService = getMoviesWebService()
    }

    fun getMovies(): List<Movie> {
        return moviesWebService.getPopularMovies().execute().body()!!.results.map { it.toModel() }
    }

}

fun getMoviesWebService(): MoviesWebService {
    val client = OkHttpClient.Builder()
    val okHttpClient = client.build()
    val gson = Gson()

    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(MOVIEDB_BASEURL)
            .client(okHttpClient)
            .build()

    return retrofit.create(MoviesWebService::class.java)
}