package com.dgimenes.architecturesample.di

import com.dgimenes.architecturesample.web.MOVIEDB_BASEURL
import com.dgimenes.architecturesample.web.MovieWebService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebServiceModule {

    @Provides
    fun provideMovieWebService(retrofit: Retrofit): MovieWebService =
            retrofit.create(MovieWebService::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MOVIEDB_BASEURL)
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}