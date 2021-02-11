package com.kotlin.submission2.ui.detail

import com.kotlin.submission2.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummyTvSeries = DataDummy.generateDummyTvSeries()[0]
    private val moviesId = dummyMovies.id
    private val tvSeriesId = dummyTvSeries.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMovies(moviesId)
        viewModel.setTvSeries(tvSeriesId)
    }

    /**
     * Memastikan data detail movies tidak null
     * Memastikan jumlah data detail movies sesuai dengan yang diharapkan
     */
    @Test
    fun getMovies() {
        viewModel.setMovies(dummyMovies.id)
        val moviesEntity = viewModel.getMovies()
        assertNotNull(moviesEntity)
        assertEquals(dummyMovies.id, moviesEntity.id)
        assertEquals(dummyMovies.title, moviesEntity.title)
        assertEquals(dummyMovies.description, moviesEntity.description)
        assertEquals(dummyMovies.genre, moviesEntity.genre)
        assertEquals(dummyMovies.yearRelease, moviesEntity.yearRelease)
        assertEquals(dummyMovies.rating, moviesEntity.rating)
        assertEquals(dummyMovies.directorOrCreator, moviesEntity.directorOrCreator)
        assertEquals(dummyMovies.stars, moviesEntity.stars)
        assertEquals(dummyMovies.metascore, moviesEntity.metascore)
        assertEquals(dummyMovies.popularity, moviesEntity.popularity)
        assertEquals(dummyMovies.bgHeader, moviesEntity.bgHeader)
        assertEquals(dummyMovies.bgPoster, moviesEntity.bgPoster)
    }

    /**
     * Memastikan data detail tv series tidak null
     * Memastikan jumlah data detail tv series sesuai dengan yang diharapkan
     */
    @Test
    fun getTvSeries() {
        viewModel.setMovies(dummyTvSeries.id)
        val tvSeriesEntity = viewModel.getTvSeries()
        assertNotNull(tvSeriesEntity)
        assertEquals(dummyTvSeries.id, tvSeriesEntity.id)
        assertEquals(dummyTvSeries.title, tvSeriesEntity.title)
        assertEquals(dummyTvSeries.description, tvSeriesEntity.description)
        assertEquals(dummyTvSeries.genre, tvSeriesEntity.genre)
        assertEquals(dummyTvSeries.yearRelease, tvSeriesEntity.yearRelease)
        assertEquals(dummyTvSeries.rating, tvSeriesEntity.rating)
        assertEquals(dummyTvSeries.directorOrCreator, tvSeriesEntity.directorOrCreator)
        assertEquals(dummyTvSeries.stars, tvSeriesEntity.stars)
        assertEquals(dummyTvSeries.metascore, tvSeriesEntity.metascore)
        assertEquals(dummyTvSeries.popularity, tvSeriesEntity.popularity)
        assertEquals(dummyTvSeries.bgHeader, tvSeriesEntity.bgHeader)
        assertEquals(dummyTvSeries.bgPoster, tvSeriesEntity.bgPoster)
    }
}