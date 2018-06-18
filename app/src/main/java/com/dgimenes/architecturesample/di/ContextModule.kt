package com.dgimenes.architecturesample.di

import com.dgimenes.architecturesample.Application
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @Provides
    fun provideAppContext() = application.applicationContext

    @Provides
    fun provideApplication() = application

}