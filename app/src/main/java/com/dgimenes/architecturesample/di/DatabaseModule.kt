package com.dgimenes.architecturesample.di

import android.content.Context
import com.dgimenes.architecturesample.data.datasource.MovieDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideMovieDAO(appContext: Context) =
            MovieDatabase.getInstance(appContext).movieDAO()

}