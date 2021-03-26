package com.kotlin.finalsub.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.kotlin.final.utils.FakeDataDummy.getDummyMoviesCast
import com.kotlin.final.utils.FakeDataDummy.getDummyMoviesDetail
import com.kotlin.final.utils.FakeDataDummy.getDummyTvSeriesCast
import com.kotlin.final.utils.FakeDataDummy.getDummyTvSeriesDetail
import com.kotlin.finalsub.data.Repository
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_MOVIES
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_TV_SERIES
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: Repository

    @Before
    fun setUp() {
        viewModel = MainViewModel(catalogueRepository)
    }

    /**
     * Movies
     */
    @Mock
    private lateinit var moviesObserver: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var moviesFavoriteObserver: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var moviesDetailObserver: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var pagedListMovies: PagedList<MoviesEntity>

    private val moviesResponse = getDummyMoviesDetail()
    private val moviesId = moviesResponse.id

    /**
     * Tv Series
     */
    @Mock
    private lateinit var tvSeriesObserver: Observer<Resource<PagedList<TvSeriesEntity>>>

    @Mock
    private lateinit var tvSeriesFavoriteObserver: Observer<PagedList<TvSeriesEntity>>

    @Mock
    private lateinit var tvSeriesDetailObserver: Observer<Resource<TvSeriesEntity>>

    @Mock
    private lateinit var pagedListTvSeries: PagedList<TvSeriesEntity>

    private val tvSeriesResponse = getDummyTvSeriesDetail()
    private val tvSeriesId = tvSeriesResponse.id

    /**
     * ****** *
     * Movies *
     * ****** *
     */
    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedListMovies)
        `when`(dummyMovies.data?.size).thenReturn(20)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getMovies()).thenReturn(movies)
        val moviesEntity = viewModel.getMovies().value?.data
        verify(catalogueRepository).getMovies()
        assertNotNull(moviesEntity)
        assertEquals(20, moviesEntity?.size)

        viewModel.getMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Test
    fun getMoviesDetail() {
        val dummyMoviesDetail = Resource.success(getDummyMoviesDetail())
        val movies = MutableLiveData<Resource<MoviesEntity>>()
        movies.value = dummyMoviesDetail
        `when`(catalogueRepository.getMoviesDetail(moviesId.toString())).thenReturn(movies)

        viewModel.setCatalogue(moviesId.toString(), BUNDLE_MOVIES)
        viewModel.getDetailMovies().observeForever(moviesDetailObserver)
        verify(moviesDetailObserver).onChanged(dummyMoviesDetail)
    }

    @Test
    fun getMoviesCast() {
        val moviesCast = MutableLiveData<List<MoviesCastItem>>()
        moviesCast.value = getDummyMoviesCast()
        `when`(catalogueRepository.getMoviesCast(moviesCast.value!!.size.toString())).thenReturn(
            moviesCast
        )
        val observer = mock(Observer::class.java)
        viewModel.getMoviesCast(moviesCast.value!!.size.toString())
            .observeForever(observer as Observer<in List<MoviesCastItem>>)
        verify(catalogueRepository).getMoviesCast(moviesCast.value!!.size.toString())
        assertNotNull(observer)
    }

    @Test
    fun getMoviesFavorite() {
        val dummyMovies = pagedListMovies
        `when`(dummyMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getMoviesFavorite("title")).thenReturn(movies)
        val moviesViewModel = viewModel.getFavoriteMovies("title").value
        verify(catalogueRepository).getMoviesFavorite("title")
        assertNotNull(moviesViewModel)
        assertEquals(5, moviesViewModel?.size)

        viewModel.getFavoriteMovies("title").observeForever(moviesFavoriteObserver)
        verify(moviesFavoriteObserver).onChanged(dummyMovies)
    }

    @Test
    fun setMoviesFavorite() {
        val dummySetMovies = Resource.success(getDummyMoviesDetail())
        val movies = MutableLiveData<Resource<MoviesEntity>>()
        movies.value = dummySetMovies
        `when`(catalogueRepository.getMoviesDetail(moviesId.toString())).thenReturn(movies)

        viewModel.setCatalogue(moviesId.toString(), BUNDLE_MOVIES)
        viewModel.insertMovies()
        verify(catalogueRepository).setMoviesFavorite(movies.value!!.data as MoviesEntity, true)
    }

    /**
     * ********* *
     * Tv Series *
     * ********* *
     */
    @Test
    fun getTvSeries() {
        val dummyTvSeries = Resource.success(pagedListTvSeries)
        `when`(dummyTvSeries.data?.size).thenReturn(20)
        val tvSeries = MutableLiveData<Resource<PagedList<TvSeriesEntity>>>()
        tvSeries.value = dummyTvSeries

        `when`(catalogueRepository.getTvSeries()).thenReturn(tvSeries)
        val tvSeriesEntity = viewModel.getTvSeries().value?.data
        verify(catalogueRepository).getTvSeries()
        assertNotNull(tvSeriesEntity)
        assertEquals(20, tvSeriesEntity?.size)

        viewModel.getTvSeries().observeForever(tvSeriesObserver)
        verify(tvSeriesObserver).onChanged(dummyTvSeries)
    }

    @Test
    fun getTvSeriesDetail() {
        val dummyTvSeriesDetail = Resource.success(getDummyTvSeriesDetail())
        val tvSeries = MutableLiveData<Resource<TvSeriesEntity>>()
        tvSeries.value = dummyTvSeriesDetail
        `when`(catalogueRepository.getTvSeriesDetail(tvSeriesId.toString())).thenReturn(tvSeries)

        viewModel.setCatalogue(tvSeriesId.toString(), BUNDLE_TV_SERIES)
        viewModel.getDetailTvSeries().observeForever(tvSeriesDetailObserver)
        verify(tvSeriesDetailObserver).onChanged(dummyTvSeriesDetail)
    }

    @Test
    fun getTvSeriesCast() {
        val tvSeriesCast = MutableLiveData<List<TvSeriesCastItem>>()
        tvSeriesCast.value = getDummyTvSeriesCast()
        `when`(catalogueRepository.getTvSeriesCast(tvSeriesCast.value!!.size.toString())).thenReturn(
            tvSeriesCast
        )
        val observer = mock(Observer::class.java)
        viewModel.getTvSeriesCast(tvSeriesCast.value!!.size.toString())
            .observeForever(observer as Observer<in List<TvSeriesCastItem>>)
        verify(catalogueRepository).getTvSeriesCast(tvSeriesCast.value!!.size.toString())
        assertNotNull(observer)
    }

    @Test
    fun getTvSeriesFavorite() {
        val dummyTvSeries = pagedListTvSeries
        `when`(dummyTvSeries.size).thenReturn(5)
        val tvSeries = MutableLiveData<PagedList<TvSeriesEntity>>()
        tvSeries.value = dummyTvSeries

        `when`(catalogueRepository.getTvSeriesFavorite("title")).thenReturn(tvSeries)
        val tvSeriesViewModel = viewModel.getFavoriteTvSeries("title").value
        verify(catalogueRepository).getTvSeriesFavorite("title")
        assertNotNull(tvSeriesViewModel)
        assertEquals(5, tvSeriesViewModel?.size)

        viewModel.getFavoriteTvSeries("title").observeForever(tvSeriesFavoriteObserver)
        verify(tvSeriesFavoriteObserver).onChanged(dummyTvSeries)
    }

    @Test
    fun setTvSeriesFavorite() {
        val dummySetTvSeries = Resource.success(getDummyTvSeriesDetail())
        val tvSeries = MutableLiveData<Resource<TvSeriesEntity>>()
        tvSeries.value = dummySetTvSeries
        `when`(catalogueRepository.getTvSeriesDetail(tvSeriesId.toString())).thenReturn(tvSeries)

        viewModel.setCatalogue(tvSeriesId.toString(), BUNDLE_TV_SERIES)
        viewModel.insertTvSeries()
        verify(catalogueRepository).setTvSeriesFavorite(tvSeries.value!!.data as TvSeriesEntity, true)
    }
}