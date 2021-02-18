package com.kotlin.submission2.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.data.repository.remote.RemoteDataSource.*
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMovies
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMoviesCast
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMoviesDetail
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeries
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeriesCast
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeriesDetail
import com.kotlin.submission2.utils.LiveDataTest
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val fakeDataRepository = FakeDataRepository(remoteDataSource)

    private val moviesId = getFakeDataDummyMovies()[0].id.toString()
    private val moviesList = getFakeDataDummyMovies()
    private val moviesCastList = getFakeDataDummyMoviesCast()
    private val moviesDetail = getFakeDataDummyMoviesDetail()

    private val tvSeriesId = getFakeDataDummyTvSeries()[0].id.toString()
    private val tvSeriesList = getFakeDataDummyTvSeries()
    private val tvSeriesCast = getFakeDataDummyTvSeriesCast()
    private val tvSeriesDetail = getFakeDataDummyTvSeriesDetail()

    private fun <T> anyOfT(type: Class<T>): T = any(type)
    private fun <T> eqOfT(some: T): T = eq(some)

    /**
     * Memanipulasi data ketika pemanggilan data movies di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data movies apakah null atau tidak
     */
    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as GetMoviesCallback)
                .onResponse(moviesList)
            null
        }.`when`(remoteDataSource).getMovies(anyOfT(GetMoviesCallback::class.java))
        val moviesEntities = LiveDataTest.getValue(fakeDataRepository.getMovies())
        verify(remoteDataSource).getMovies(anyOfT(GetMoviesCallback::class.java))
        assertNotNull(moviesEntities)
    }

    /**
     * Memanipulasi data ketika pemanggilan data movies cast di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data movies cast apakah null atau tidak
     */
    @Test
    fun getMoviesCast() {
        doAnswer { invocation ->
            (invocation.arguments[1] as GetMoviesCastCallback)
                .onResponse(moviesCastList)
            null
        }.`when`(remoteDataSource).getMoviesCast(
            eqOfT(moviesId),
            anyOfT(GetMoviesCastCallback::class.java)
        )
        val moviesCastEntities = LiveDataTest.getValue(fakeDataRepository.getMoviesCast(moviesId))
        verify(remoteDataSource).getMoviesCast(
            eqOfT(moviesId),
            anyOfT(GetMoviesCastCallback::class.java)
        )
        assertNotNull(moviesCastEntities)
    }

    /**
     * Memanipulasi data ketika pemanggilan data movies detail di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data movies detail apakah null atau tidak
     */
    @Test
    fun getMoviesDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as GetMoviesDetailCallback)
                .onResponse(moviesDetail)
            null
        }.`when`(remoteDataSource).getMoviesDetail(
            eqOfT(moviesId),
            anyOfT(GetMoviesDetailCallback::class.java)
        )
        val moviesDetailEntities =
            LiveDataTest.getValue(fakeDataRepository.getMoviesDetail(moviesId))
        verify(remoteDataSource).getMoviesDetail(
            eqOfT(moviesId),
            anyOfT(GetMoviesDetailCallback::class.java)
        )
        assertNotNull(moviesDetailEntities)
    }

    /**
     * Memanipulasi data ketika pemanggilan data tv series di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data tv series apakah null atau tidak
     */
    @Test
    fun getTvSeries() {
        doAnswer { invocation ->
            (invocation.arguments[0] as GetTvSeriesCallback)
                .onResponse(tvSeriesList)
            null
        }.`when`(remoteDataSource).getTvSeries(anyOfT(GetTvSeriesCallback::class.java))
        val tvSeriesEntities = LiveDataTest.getValue(fakeDataRepository.getTvSeries())
        verify(remoteDataSource).getTvSeries(anyOfT(GetTvSeriesCallback::class.java))
        assertNotNull(tvSeriesEntities)
    }

    /**
     * Memanipulasi data ketika pemanggilan data tv series cast di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data tv series cast apakah null atau tidak
     */
    @Test
    fun getTvSeriesCast() {
        doAnswer { invocation ->
            (invocation.arguments[1] as GetTvSeriesCastCallback)
                .onResponse(tvSeriesCast)
            null
        }.`when`(remoteDataSource).getTvSeriesCast(
            eqOfT(tvSeriesId),
            anyOfT(GetTvSeriesCastCallback::class.java)
        )
        val tvSeriesCastEntities =
            LiveDataTest.getValue(fakeDataRepository.getTvSeriesCast(tvSeriesId))
        verify(remoteDataSource).getTvSeriesCast(
            eqOfT(tvSeriesId),
            anyOfT(GetTvSeriesCastCallback::class.java)
        )
        assertNotNull(tvSeriesCastEntities)
    }

    /**
     * Memanipulasi data ketika pemanggilan data tv series detail di kelas repository
     * Memastikan metode di kelas Repository terpanggil
     * Melakukan pengecekan data tv series detail apakah null atau tidak
     */
    @Test
    fun getTvSeriesDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as GetTvSeriesDetailCallback)
                .onResponse(tvSeriesDetail)
            null
        }.`when`(remoteDataSource).getTvSeriesDetail(
            eqOfT(tvSeriesId),
            anyOfT(GetTvSeriesDetailCallback::class.java)
        )
        val tvSeriesDetailEntities =
            LiveDataTest.getValue(fakeDataRepository.getTvSeriesDetail(tvSeriesId))
        verify(remoteDataSource).getTvSeriesDetail(
            eqOfT(tvSeriesId),
            anyOfT(GetTvSeriesDetailCallback::class.java)
        )
        assertNotNull(tvSeriesDetailEntities)
    }

}