package com.dgimenes.architecturesample.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.movieslist.MovieListActivity
import com.dgimenes.architecturesample.movieslist.MovieListViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MovieListModule::class))
interface MovieListComponent {

    fun inject(activity: MovieListActivity)

}

@Module
class MovieListModule {

    @Provides
    fun provideMovieListViewModelFactory(movieRepository: MovieRepository) =
            MovieListViewModelFactory(movieRepository)

}

class MovieListViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieRepository) as T
    }

}
