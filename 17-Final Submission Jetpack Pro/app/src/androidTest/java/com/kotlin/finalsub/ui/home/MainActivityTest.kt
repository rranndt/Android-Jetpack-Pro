package com.kotlin.finalsub.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kotlin.finalsub.R
import com.kotlin.finalsub.ui.MainActivity
import com.kotlin.finalsub.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }


    /**
     * Memastikan rv_movies dalam keadaan tampil
     * Gulir rv_movies ke posisi pertama
     */
    @Test
    fun loadMovies() {
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
     * Mematikan iv_main_header dalam keadaan tampil
     * Mematikan iv_main_poster dalam keadaan tampil
     * Mematikan tv_main_title dalam keadaan tampil
     * Mematikan tv_main_year dalam keadaan tampil
     * Mematikan tv_main_runtime dalam keadaan tampil
     * Mematikan tv_main_rating dalam keadaan tampil
     * Mematikan tv_main_genre dalam keadaan tampil
     * Mematikan tv_main_user_score dalam keadaan tampil
     * Mematikan tv_main_description dalam keadaan tampil
     * Mematikan tv_main_popularity dalam keadaan tampil
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
        onView(withId(R.id.iv_main_header)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_main_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    /**
     * Memberi tindakan klik pada menu bottom navigation tvSeriesFragment
     * Memastikan rv_tvseries dalam keadaan tampil
     * Gulir rv_tvseries ke posisi pertama
     */
    @Test
    fun loadTvSeries() {
        onView(withId(R.id.tvSeriesFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSeriesFragment)).perform(click())
        onView(withId(R.id.rv_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    /**
     * Memberi tindakan klik pada menu bottom navigation tvSeriesFragment
     * Memastikan rv_tvseries dalam keadaan tampil
     * Memberi tindakan pada data pertama di rv_tvseries
     * Mematikan iv_main_header dalam keadaan tampil
     * Mematikan iv_main_poster dalam keadaan tampil
     * Mematikan tv_main_title dalam keadaan tampil
     * Mematikan tv_main_year dalam keadaan tampil
     * Mematikan tv_main_runtime dalam keadaan tampil
     * Mematikan tv_main_rating dalam keadaan tampil
     * Mematikan tv_main_genre dalam keadaan tampil
     * Mematikan tv_main_user_score dalam keadaan tampil
     * Mematikan tv_main_description dalam keadaan tampil
     * Mematikan tv_main_popularity dalam keadaan tampil
     * Memastikan rv_cast dalam keadaan tampil
     * Gulir rv_cast ke posisi pertama
     */
    @Test
    fun loadDetailTvSeries() {
        onView(withId(R.id.tvSeriesFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSeriesFragment)).perform(click())
        onView(withId(R.id.rv_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_main_header)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_main_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_main_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_cast)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

}