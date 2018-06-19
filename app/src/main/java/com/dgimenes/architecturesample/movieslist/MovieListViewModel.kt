package com.dgimenes.architecturesample.movieslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.data.model.Movie
import com.dgimenes.architecturesample.data.model.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }

    private val movies: MutableLiveData<Resource<List<Movie>>> by lazy {
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        liveData.value = Resource(listOf(), LoadingState.LOADING)
        disposables.add(
                movieRepository.getPopularMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            liveData.value = Resource(movies, LoadingState.SUCCESS)
                        }, { error ->
                            liveData.value = Resource(listOf(), LoadingState.ERROR, error.message)
                            error.printStackTrace()
                        })
        )
        liveData
    }

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
            movies

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}

enum class LoadingState {
    LOADING, SUCCESS, ERROR
}
