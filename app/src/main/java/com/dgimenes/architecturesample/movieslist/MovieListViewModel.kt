package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.data.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }

    private val movies: MutableLiveData<List<Movie>> by lazy {
        val liveData = MutableLiveData<List<Movie>>()
        disposables.add(
                movieRepository.getPopularMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            liveData.value = movies
                        }, { error ->
                            liveData.value = listOf()
                            // TODO BETTER ERROR HANDLING
                            error.printStackTrace()
                        })
        )
        liveData
    }

    fun getPopularMovies(): LiveData<List<Movie>> =
            movies

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
