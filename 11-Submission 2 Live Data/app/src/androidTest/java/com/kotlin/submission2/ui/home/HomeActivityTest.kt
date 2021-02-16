package com.kotlin.submission2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kotlin.submission2.R
import com.kotlin.submission2.utils.DataDummy
import org.junit.Before
import org.junit.Test

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvSeries = DataDummy.generateDummyTvSeries()

    private val metascoreMovies = dummyMovies[0].metascore.toInt()
    private val metascoreTvSeries = dummyTvSeries[0].metascore.toInt()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    /**
     * Memastikan rv_movies dalam keadaan tampil
     * Scroll rv_movies ke posisi terakhir
     */
    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    /**
     * Memberi tindakan klik pada data pertama di rv_movies
     * Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk description tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk genre tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk year tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk rating tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk director tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk stars tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk metascrore tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk reviews tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk popularity tampil sesuai dengan yang diharapkan
     * Memastikan ImageView untuk poster tampil sesuai dengan yang diharapkan
     * Memastikan ImageView untuk header tampil sesuai dengan yang diharapkan
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
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyMovies[0].description)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovies[0].genre)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyMovies[0].yearRelease)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyMovies[0].rating)))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(withText("Director : ${dummyMovies[0].directorOrCreator}")))
        onView(withId(R.id.tv_stars)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars)).check(matches(withText("Stars : ${dummyMovies[0].stars}")))
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(withText(metascoreMovies.toString())))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyMovies[0].reviews)))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyMovies[0].popularity)))
        onView(withId(R.id.cv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_header)).check(matches(isDisplayed()))
    }

    /**
     * Memastikan rv_tvseries dalam keadaan tampil
     * Scroll rv_tvseries ke posisi terakhir
     */
    @Test
    fun loadTvSeries() {
        onView(withText("TV SERIES")).perform(click())
        onView(withId(R.id.rv_tvseries)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvseries)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvSeries.size
            )
        )
    }

    /**
     * Memberi tindakan klik pada data pertama di rv_tvseries
     * Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk description tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk genre tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk year tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk rating tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk director tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk stars tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk metascrore tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk reviews tampil sesuai dengan yang diharapkan
     * Memastikan TextView untuk popularity tampil sesuai dengan yang diharapkan
     * Memastikan ImageView untuk poster tampil sesuai dengan yang diharapkan
     * Memastikan ImageView untuk header tampil sesuai dengan yang diharapkan
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
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvSeries[0].title)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyTvSeries[0].description)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyTvSeries[0].genre)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyTvSeries[0].yearRelease)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyTvSeries[0].rating)))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(withText("Director : ${dummyTvSeries[0].directorOrCreator}")))
        onView(withId(R.id.tv_stars)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars)).check(matches(withText("Stars : ${dummyTvSeries[0].stars}")))
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(withText(metascoreTvSeries.toString())))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyTvSeries[0].reviews)))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyTvSeries[0].popularity)))
        onView(withId(R.id.cv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_header)).check(matches(isDisplayed()))
    }

}