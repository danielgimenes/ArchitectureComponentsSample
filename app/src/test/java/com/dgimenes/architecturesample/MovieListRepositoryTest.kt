package com.dgimenes.architecturesample

import com.dgimenes.architecturesample.data.MovieRepository
import com.dgimenes.architecturesample.data.datasource.MovieDAO
import com.dgimenes.architecturesample.data.model.Movie
import com.dgimenes.architecturesample.utils.RxJavaSchedulersSetupRule
import com.dgimenes.architecturesample.web.MovieDTO
import com.dgimenes.architecturesample.web.MovieWebService
import com.dgimenes.architecturesample.web.PopularMoviesDTO
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class MovieListRepositoryTest {

    companion object {
        @Suppress("unused")
        @get:ClassRule
        @JvmStatic
        val rxJavaSchedulersRule = RxJavaSchedulersSetupRule()
    }

    @Test
    fun moviesCached_getPopularMovies_returnListOfMoviesFromDao() {
        val fakeMovies = listOf(
                Movie(id = 1, title = "Jurassic World"),
                Movie(id = 2, title = "Thor"),
                Movie(id = 3, title = "Guardians of The Galaxy")
        )
        val mockWebService = Mockito.mock(MovieWebService::class.java)
        val mockDao = Mockito.mock(MovieDAO::class.java)
        `when`(mockDao.getMovies()).thenReturn(Single.just(fakeMovies))
        `when`(mockWebService.getPopularMovies()).thenReturn(Single.just(PopularMoviesDTO(listOf())))
        val movieRepository = MovieRepository(mockWebService, mockDao)

        val result = movieRepository.getPopularMovies()
        val fetchedMovies = result.blockingGet()

        assertEquals(fakeMovies, fetchedMovies)
    }

    @Test
    fun moviesNotCached_getPopularMovies_returnListOfMoviesFromWebServiceAndUpdatesDao() {
        val fakeMovies = listOf(
                MovieDTO(id = 1, title = "Jurassic World"),
                MovieDTO(id = 2, title = "Thor"),
                MovieDTO(id = 3, title = "Guardians of The Galaxy")
        )
        val mockWebService = Mockito.mock(MovieWebService::class.java)
        val mockDao = Mockito.mock(MovieDAO::class.java)
        `when`(mockDao.getMovies()).thenReturn(Single.just(listOf()))
        `when`(mockWebService.getPopularMovies()).thenReturn(Single.just(PopularMoviesDTO(fakeMovies)))
        val movieRepository = MovieRepository(mockWebService, mockDao)

        val result = movieRepository.getPopularMovies()
        val fetchedMovies = result.blockingGet()

        assertEquals(fakeMovies.map { it.toModel() }, fetchedMovies)
        verify(mockDao, times(1)).saveMovies(fetchedMovies)
    }

}