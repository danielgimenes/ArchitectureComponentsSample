package com.dgimenes.architecturesample

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dgimenes.architecturesample.movieslist.MovieListActivity
import com.dgimenes.architecturesample.utils.MockWebServerRule
import com.dgimenes.architecturesample.utils.OkHttpIdlingResourceRule
import com.dgimenes.architecturesample.web.MovieDTO
import com.dgimenes.architecturesample.web.PopularMoviesDTO
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListTest {
    @Suppress("unused")
    @get:Rule
    val activityRule = ActivityTestRule<MovieListActivity>(
            MovieListActivity::class.java,
            true,
            false
    )

    @Suppress("unused")
    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Suppress("unused")
    @get:Rule
    val okHttpIdlingResourceRule = OkHttpIdlingResourceRule()

    @Before
    fun setBaseUrl() {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as Application
        app.webServerBaseUrl = mockWebServerRule.server.url("/").toString()
    }

    @Test
    fun noCacheAvailable_loadsMovieList_displaysDataFromServer() {
        val fakeMovies = listOf(
                MovieDTO(id = 1, title = "Jurassic World"),
                MovieDTO(id = 2, title = "Thor"),
                MovieDTO(id = 3, title = "Guardians of The Galaxy")
        )
        mockWebServerRule.server.enqueue(MockResponse().setBody(Gson().toJson(PopularMoviesDTO(fakeMovies))))

        activityRule.launchActivity(null)

        fakeMovies.forEach {
            onView(withText(it.title)).check(matches(isDisplayed()))
        }
    }

}
