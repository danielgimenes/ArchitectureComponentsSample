package com.dgimenes.architecturesample.di

import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = arrayOf(
        RepositoryModule::class,
        WebServiceModule::class,
        ContextModule::class,
        DatabaseModule::class
))
interface BaseGraphComponent {

    fun newMovieListComponent(movieListModule: MovieListModule): MovieListComponent

    fun okHttpClient(): OkHttpClient
}