package com.kotlin.submission2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kotlin.submission2.FakeDataDummy
import com.kotlin.submission2.FakeDataDummy.getFakeDataDummyMoviesDetail
import com.kotlin.submission2.FakeDataDummy.getFakeDataDummyTvSeriesDetail
import com.kotlin.submission2.R
import com.kotlin.submission2.utils.Constant
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class HomeActivityTest {

    private val dummyMovies = getFakeDataDummyMoviesDetail()
    private val dummyTvSeries = getFakeDataDummyTvSeriesDetail()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }


    /**
     * Memastikan carousel_view dalam keadaan tampil
     * Memastikan rv_movies dalam keadaan tampil
     * Gulir rv_movies ke posisi pertama
     */
    @Test
    fun loadMovies() {
        onView(withId(R.id.carousel_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    /**
     * Memastikan rv_movies dalam keadaan tampil
     * Memberi tindakan pada data pertama di rv_movies
     * Memastikan TextView untuk tv_title tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_year tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_rating tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_runtime tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_user_score tampil sesuai dengan yang diharapkan
     * Memastikan rv_cast dalam keadaan tampil
     * Gulir rv_cast ke posisi pertama
     */
    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.tv_title), hasSibling(withText("Wonder Woman 1984")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_title), hasSibling(withText("Wonder Woman 1984")))).check(
            matches(withText(dummyMovies.title))
        )
        onView(allOf(withId(R.id.tv_year), hasSibling(withText("2020")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_year), hasSibling(withText("2020")))).check(
            matches(withText(dummyMovies.releaseDate))
        )
        onView(allOf(withId(R.id.tv_rating), hasSibling(withText("6.9")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_rating), hasSibling(withText("6.9")))).check(
            matches(withText(dummyMovies.voteAverage.toString()))
        )
        onView(allOf(withId(R.id.tv_runtime), hasSibling(withText("152 Minutes")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_runtime), hasSibling(withText("152 Minutes")))).check(
            matches(withText("${dummyMovies.runtime} Minutes"))
        )
        onView(allOf(withId(R.id.tv_user_score), hasSibling(withText("6.9")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_user_score), hasSibling(withText("6.9")))).check(
            matches(withText(dummyMovies.voteAverage.toString()))
        )
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    /**
     * Memastikan carousel_view dalam keadaan tampil
     * Memastikan rv_tvseries dalam keadaan tampil
     * Gulir rv_tvseries ke posisi pertama
     */
    @Test
    fun loadTvSeries() {
        onView(withText("TV SERIES")).perform(click())
        onView(withId(R.id.rv_tvseries)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvseries)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    /**
     * Memberi tindakan pada text "TV SERIES" (View Pager)
     * Memastikan rv_tvseries dalam keadaan tampil
     * Memberi tindakan pada data pertama di rv_tvseries
     * Memastikan TextView untuk tv_title tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_year tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_rating tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk tv_user_score tampil sesuai dengan yang diharapkan
     * Memastikan rv_cast dalam keadaan tampil
     * Gulir rv_cast ke posisi pertama
     */
    @Test
    fun loadDetailTvSeries() {
        onView(withText("TV SERIES")).perform(click())
        onView(withId(R.id.rv_tvseries)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvseries)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.tv_title), hasSibling(withText("WandaVision")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_title), hasSibling(withText("WandaVision")))).check(
            matches(withText(dummyTvSeries.originalName))
        )
        onView(allOf(withId(R.id.tv_year), hasSibling(withText("2021")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_year), hasSibling(withText("2021")))).check(
            matches(withText(dummyTvSeries.firstAirDate))
        )
        onView(allOf(withId(R.id.tv_rating), hasSibling(withText("8.4")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_rating), hasSibling(withText("8.4")))).check(
            matches(withText(dummyTvSeries.voteAverage.toString()))
        )
        onView(allOf(withId(R.id.tv_user_score), hasSibling(withText("8.4")))).check(
            matches(isDisplayed())
        )
        onView(allOf(withId(R.id.tv_user_score), hasSibling(withText("8.4")))).check(
            matches(withText(dummyTvSeries.voteAverage.toString()))
        )
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

}