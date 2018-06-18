package com.dgimenes.architecturesample.di

import dagger.Component

@Component(modules = arrayOf(
        RepositoryModule::class,
        WebServiceModule::class,
        ContextModule::class,
        DatabaseModule::class
))
interface BaseGraphComponent {

    fun newMovieListComponent(movieListModule: MovieListModule): MovieListComponent

}