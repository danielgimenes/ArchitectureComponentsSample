package com.dgimenes.architecturesample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dgimenes.architecturesample.movieslist.MovieListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListTest {

    @Suppress("unused")
    @get:Rule
    val activityRule = ActivityTestRule<MovieListActivity>(MovieListActivity::class.java)

    @Test
    fun loadsMovieList() {
        onView(withText("Jurassic World")).check(matches(isDisplayed()))
    }

}
