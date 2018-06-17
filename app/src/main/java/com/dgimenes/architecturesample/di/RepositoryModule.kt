package com.dgimenes.architecturesample.di

import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.web.MovieWebService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMovieRepository(movieWebService: MovieWebService) =
            MovieRepository(movieWebService)

}