package com.dgimenes.architecturesample.data

import com.dgimenes.architecturesample.data.model.Movie
import com.dgimenes.architecturesample.web.MOVIEDB_BASEURL
import com.dgimenes.architecturesample.web.MovieWebService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private val movieWebService: MovieWebService by lazy {
        getMoviesWebService()
    }

    fun getPopularMovies(): Observable<List<Movie>> =
            movieWebService.getPopularMovies()
                    .map {
                        it.results.map { it.toModel() }
                    }
}

fun getMoviesWebService(): MovieWebService {
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

    return retrofit.create(MovieWebService::class.java)
}