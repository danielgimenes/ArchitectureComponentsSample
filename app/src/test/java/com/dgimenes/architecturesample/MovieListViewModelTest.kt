package com.dgimenes.architecturesample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.data.model.Movie
import com.dgimenes.architecturesample.data.model.Resource
import com.dgimenes.architecturesample.movieslist.MovieListViewModel
import com.dgimenes.architecturesample.utils.RxJavaSchedulersSetupRule
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MovieListViewModelTest {

    companion object {
        @Suppress("unused")
        @get:ClassRule
        @JvmStatic
        val rxJavaSchedulersRule = RxJavaSchedulersSetupRule()

        @Suppress("unused")
        @get:ClassRule
        @JvmStatic
        val rule: TestRule = InstantTaskExecutorRule()
    }

    @Suppress("FunctionName")
    @Test
    fun getPopularMovies_returnLiveDataWithMovies() {
        val fakeMovies = listOf(
                Movie(id = 1, title = "Jurassic World"),
                Movie(id = 2, title = "Thor"),
                Movie(id = 3, title = "Guardians of The Galaxy")
        )
        val movieRepository = Mockito.mock(MovieRepository::class.java)
        `when`(movieRepository.getPopularMovies()).thenReturn(Single.just(fakeMovies))
        val viewModel = MovieListViewModel(movieRepository)

        val resultLiveData = viewModel.getPopularMovies()

        @Suppress("USELESS_IS_CHECK")
        assert(resultLiveData is LiveData<Resource<List<Movie>>>)
        assertEquals(fakeMovies, resultLiveData.value!!.data)
    }

}

