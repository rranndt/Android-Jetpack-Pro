package com.kotlin.finalsub.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kotlin.final.utils.FakeDataDummy.getDummyMovies
import com.kotlin.final.utils.FakeDataDummy.getDummyMoviesCast
import com.kotlin.final.utils.FakeDataDummy.getDummyMoviesDetail
import com.kotlin.final.utils.FakeDataDummy.getDummyRemoteMoviesDetail
import com.kotlin.final.utils.FakeDataDummy.getDummyRemoteTvSeriesDetail
import com.kotlin.final.utils.FakeDataDummy.getDummyTvSeries
import com.kotlin.final.utils.FakeDataDummy.getDummyTvSeriesCast
import com.kotlin.final.utils.FakeDataDummy.getDummyTvSeriesDetail
import com.kotlin.finalsub.data.source.local.LocalDataSource
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.RemoteDataSource
import com.kotlin.finalsub.utils.AppExecutors
import com.kotlin.finalsub.utils.Constant.Companion.TITLE
import com.kotlin.finalsub.utils.Helper.joinGenres
import com.kotlin.finalsub.utils.LiveDataTestUtil.getValue
import com.kotlin.finalsub.utils.PagedListUtil.mockPagedList
import com.kotlin.finalsub.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository =
        FakeCatalogueRepository(remote, local, appExecutors)

    /**
     * Movies
     */
    private val moviesResponses = getDummyMovies()
    private val moviesId = moviesResponses[0].id
    private val moviesCastResponse = getDummyMoviesCast()
    private val moviesDetailResponse = getDummyRemoteMoviesDetail()

    /**
     * Tv Series
     */
    private val tvSeriesResponses = getDummyTvSeries()
    private val tvSeriesId = tvSeriesResponses[0].id
    private val tvSeriesCastResponse = getDummyTvSeriesCast()
    private val tvSeriesDetailResponse = getDummyRemoteTvSeriesDetail()

    private fun <T> anyOfT(type: Class<T>): T = any(type)
    private fun <T> eqOfT(some: T): T = eq(some)

    /**
     * ****** *
     * Movies *
     * ****** *
     */
    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovies()

        val moviesEntities = Resource.success(mockPagedList(getDummyMovies()))
        verify(local).getMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponses.size, moviesEntities.data?.size)
    }

    @Test
    fun getMoviesDetail() {
        val moviesDetail = MutableLiveData<MoviesEntity>()
        moviesDetail.value = getDummyMoviesDetail()
        `when`(local.getMoviesById(moviesId.toString())).thenReturn(moviesDetail)

        val moviesDetailEntities =
            getValue(catalogueRepository.getMoviesDetail(moviesId.toString()))
        verify(local).getMoviesById(moviesId.toString())
        assertNotNull(moviesDetailEntities)
        assertEquals(moviesDetailResponse.id, moviesDetailEntities.data?.id)
        assertEquals(moviesDetailResponse.title, moviesDetailEntities.data?.title)
        assertEquals(moviesDetailResponse.posterPath, moviesDetailEntities.data?.posterPath)
        assertEquals(moviesDetailResponse.backdropPath, moviesDetailEntities.data?.backdropPath)
        assertEquals(moviesDetailResponse.releaseDate, moviesDetailEntities.data?.releaseDate)
        assertEquals(moviesDetailResponse.voteAverage, moviesDetailEntities.data?.voteAverage)
        assertEquals(moviesDetailResponse.runtime, moviesDetailEntities.data?.runtime)
        assertEquals(joinGenres(moviesDetailResponse), moviesDetailEntities.data?.genres)
        assertEquals(moviesDetailResponse.popularity, moviesDetailEntities.data?.popularity)
        assertEquals(moviesDetailResponse.overview, moviesDetailEntities.data?.overview)
    }

    @Test
    fun getMoviesCast() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetMoviesCastCallback)
                .onResponse(moviesCastResponse)
            null
        }.`when`(remote).getMoviesCast(
            eqOfT(moviesId.toString()),
            anyOfT(RemoteDataSource.GetMoviesCastCallback::class.java)
        )
        val moviesCastEntities =
            getValue(catalogueRepository.getMoviesCast(moviesId.toString()))
        verify(remote).getMoviesCast(
            eqOfT(moviesId.toString()),
            anyOfT(RemoteDataSource.GetMoviesCastCallback::class.java)
        )
        assertNotNull(moviesCastEntities)
        assertEquals(moviesCastEntities.size, moviesCastResponse.size)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getMoviesFavorite(TITLE)).thenReturn(dataSourceFactory)
        catalogueRepository.getMoviesFavorite(TITLE)

        val dummyMoviesEntity = Resource.success(mockPagedList(getDummyMovies()))
        verify(local).getMoviesFavorite(TITLE)
        assertNotNull(dummyMoviesEntity.data)
        assertEquals(moviesResponses.size, dummyMoviesEntity.data?.size)
    }

    @Test
    fun setFavoriteMovies() {
        catalogueRepository.setMoviesFavorite(getDummyMoviesDetail(), true)
        verify(local).updateMovies(getDummyMoviesDetail(), true)
    }

    /**
     * ********* *
     * Tv Series *
     * ********* *
     */
    @Test
    fun getTvSeries() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvSeriesEntity>
        `when`(local.getTvSeries()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvSeries()
        val tvSeriesEntities = Resource.success(mockPagedList(getDummyTvSeries()))
        verify(local).getTvSeries()
        assertNotNull(tvSeriesEntities.data)
        assertEquals(tvSeriesResponses.size, tvSeriesEntities.data?.size)
    }

    @Test
    fun getTvSeriesDetail() {
        val tvSeriesDetail = MutableLiveData<TvSeriesEntity>()
        tvSeriesDetail.value = getDummyTvSeriesDetail()
        `when`(local.getTvSeriesById(tvSeriesId.toString())).thenReturn(tvSeriesDetail)

        val tvSeriesDetailEntities =
            getValue(catalogueRepository.getTvSeriesDetail(tvSeriesId.toString()))
        verify(local).getTvSeriesById(tvSeriesId.toString())
        assertNotNull(tvSeriesDetailEntities)
        assertEquals(tvSeriesDetailResponse.id, tvSeriesDetailEntities.data?.id)
        assertEquals(tvSeriesDetailResponse.posterPath, tvSeriesDetailEntities.data?.posterPath)
        assertEquals(tvSeriesDetailResponse.backdropPath, tvSeriesDetailEntities.data?.backdropPath)
        assertEquals(tvSeriesDetailResponse.firstAirDate, tvSeriesDetailEntities.data?.releaseData)
        assertEquals(tvSeriesDetailResponse.voteAverage, tvSeriesDetailEntities.data?.voteAverage)
        assertEquals(joinGenres(tvSeriesDetailResponse), tvSeriesDetailEntities.data?.genres)
        assertEquals(tvSeriesDetailResponse.popularity, tvSeriesDetailEntities.data?.popularity)
        assertEquals(tvSeriesDetailResponse.overview, tvSeriesDetailEntities.data?.overview)
    }

    @Test
    fun getTvSeriesCast() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetTvSeriesCastCallback)
                .onResponse(tvSeriesCastResponse)
            null
        }.`when`(remote).getTvSeriesCast(
            eqOfT(tvSeriesId.toString()),
            anyOfT(RemoteDataSource.GetTvSeriesCastCallback::class.java)
        )
        val tvSeriesCastListEntities =
            getValue(catalogueRepository.getTvSeriesCast(tvSeriesId.toString()))
        verify(remote).getTvSeriesCast(
            eqOfT(tvSeriesId.toString()),
            anyOfT(RemoteDataSource.GetTvSeriesCastCallback::class.java)
        )
        assertNotNull(tvSeriesCastListEntities)
        assertEquals(tvSeriesCastResponse.size, tvSeriesCastListEntities.size)
    }

    @Test
    fun getFavoriteTvSeries() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvSeriesEntity>
        `when`(local.getTvSeriesFavorite(TITLE)).thenReturn(dataSourceFactory)
        catalogueRepository.getTvSeriesFavorite(TITLE)
        val dummyTvSeriesEntity = Resource.success(mockPagedList(getDummyTvSeries()))
        verify(local).getTvSeriesFavorite(TITLE)
        assertNotNull(dummyTvSeriesEntity.data)
        assertEquals(tvSeriesResponses.size, dummyTvSeriesEntity.data?.size)
    }

    @Test
    fun setFavoriteTvSeries() {
        catalogueRepository.setTvSeriesFavorite(getDummyTvSeriesDetail(), true)
        verify(local).updateTvSeries(getDummyTvSeriesDetail(), true)
    }

}