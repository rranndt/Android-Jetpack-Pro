package com.kotlin.submission2.ui.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    /**
     * Memastikan data movies tidak null
     * Memastikan jumlah data movies sesuai dengan yang diharapkan
     */
    @Test
    fun getListMovies() {
        val movies = viewModel.getMoviesDetail()
        assertNotNull(movies)
        assertEquals(20, movies.size)
    }

    /**
     * Memastikan data tv series tidak null
     * Memastikan jumlah data tv series sesuai dengan yang diharapkan
     */
    @Test
    fun getListTvSeries() {
        val tvSeries = viewModel.getTvSeriesDetail()
        assertNotNull(tvSeries)
        assertEquals(20, tvSeries.size)
    }
}