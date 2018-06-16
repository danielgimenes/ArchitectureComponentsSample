package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.Movie
import com.dgimenes.architecturesample.web.MOVIEDB_BASEURL
import com.dgimenes.architecturesample.web.MoviesWebService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListViewModel : ViewModel() {

    private lateinit var moviesWebService: MoviesWebService

    fun init() {
        moviesWebService = getMoviesWebService()
    }

    fun loadPopularMovies(): Observable<List<Movie>> =
            moviesWebService.getPopularMovies()
                    .map {
                        it.results.map { it.toModel() }
                    }

}

fun getMoviesWebService(): MoviesWebService {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val gson = Gson()

    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(MOVIEDB_BASEURL)
            .client(okHttpClient)
            .build()

    return retrofit.create(MoviesWebService::class.java)
}