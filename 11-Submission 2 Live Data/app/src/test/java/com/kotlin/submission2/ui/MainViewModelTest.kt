package com.kotlin.submission2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.submission2.data.repository.DataRepository
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMovies
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMoviesCast
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyMoviesDetail
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeries
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeriesCast
import com.kotlin.submission2.utils.FakeDataDummy.getFakeDataDummyTvSeriesDetail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private val observer = Observer::class.java

    private var viewModel: MainViewModel? = null
    private var dataRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MainViewModel(dataRepository)
    }

    /**
     * Memanipulasi data ketika pemanggilan data movies di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     */
    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<MoviesListItem>>()
        movies.value = getFakeDataDummyMovies()
        `when`(dataRepository.getMovies()).thenReturn(movies)
        viewModel?.movies?.observeForever(observer as Observer<in List<MoviesListItem>>)
        verify(dataRepository).getMovies()
        assertNotNull(observer)
    }

    /**
     * Memanipulasi data ketika pemanggilan data movies detail di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     * Melakukan pengecekan data id apakah sudah sesuai atau belum
     */
    @Test
    fun getMoviesDetail() {
        val moviesDetail = MutableLiveData<MoviesDetailItem>()
        moviesDetail.value = getFakeDataDummyMoviesDetail()
        `when`(dataRepository.getMoviesDetail(moviesDetail.value!!.id.toString())).thenReturn(
            moviesDetail
        )
        val observer = mock(Observer::class.java)
        viewModel?.getMoviesDetail(moviesDetail.value!!.id.toString())
            ?.observeForever(observer as Observer<in MoviesDetailItem>)
        verify(dataRepository).getMoviesDetail(moviesDetail.value!!.id.toString())
        assertNotNull(observer)
        assertEquals(
            moviesDetail.value!!.id,
            viewModel?.getMoviesDetail(moviesDetail.value!!.id.toString())?.value?.id
        )
    }

    /**
     * Memanipulasi data ketika pemanggilan data cast movies di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     */
    @Test
    fun getMoviesCast() {
        val moviesCast = MutableLiveData<List<MoviesCastItem>>()
        moviesCast.value = getFakeDataDummyMoviesCast()
        `when`(dataRepository.getMoviesCast(moviesCast.value!!.size.toString())).thenReturn(
            moviesCast
        )
        val observer = mock(Observer::class.java)
        viewModel?.getMoviesCast(moviesCast.value!!.size.toString())
            ?.observeForever(observer as Observer<in List<MoviesCastItem>>)
        verify(dataRepository).getMoviesCast(moviesCast.value!!.size.toString())
        assertNotNull(observer)
    }

    /**
     * Memanipulasi data ketika pemanggilan tv series di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     */
    @Test
    fun getTvSeries() {
        val tvSeries = MutableLiveData<List<TvSeriesListItem>>()
        tvSeries.value = getFakeDataDummyTvSeries()
        `when`(dataRepository.getTvSeries()).thenReturn(tvSeries)
        viewModel?.tvSeries?.removeObserver(observer as Observer<in List<TvSeriesListItem>>)
        verify(dataRepository).getTvSeries()
        assertNotNull(observer)
    }

    /**
     * Memanipulasi data ketika pemanggilan data tv series detail di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     * Melakukan pengecekan data id apakah sudah sesuai atau belum
     */
    @Test
    fun getTvSeriesDetail() {
        val tvSeriesDetail = MutableLiveData<TvSeriesDetailItem>()
        tvSeriesDetail.value = getFakeDataDummyTvSeriesDetail()
        `when`(dataRepository.getTvSeriesDetail(tvSeriesDetail.value!!.id.toString())).thenReturn(
            tvSeriesDetail
        )
        val observer = mock(Observer::class.java)
        viewModel?.getTvSeriesDetail(tvSeriesDetail.value!!.id.toString())
            ?.observeForever(observer as Observer<in TvSeriesDetailItem>)
        verify(dataRepository).getTvSeriesDetail(tvSeriesDetail.value!!.id.toString())
        assertNotNull(observer)
        assertEquals(
            tvSeriesDetail.value!!.id,
            viewModel?.getTvSeriesDetail(tvSeriesDetail.value!!.id.toString())?.value?.id
        )
    }

    /**
     * Memanipulasi data ketika pemanggilan cast tv series di kelas repository
     * Memastikan motode di kelas repository terpanggil
     * Melakukan pengecekan data apakah null atau tidak
     */
    @Test
    fun getTvSeriesCast() {
        val tvSeriesCast = MutableLiveData<List<TvSeriesCastItem>>()
        tvSeriesCast.value = getFakeDataDummyTvSeriesCast()
        `when`(dataRepository.getTvSeriesCast(tvSeriesCast.value!!.size.toString())).thenReturn(
            tvSeriesCast
        )
        val observer = mock(Observer::class.java)
        viewModel?.getMoviesCast(tvSeriesCast.value!!.size.toString())
            ?.observeForever(observer as Observer<in List<MoviesCastItem>>)
        verify(dataRepository).getMoviesCast(tvSeriesCast.value!!.size.toString())
        assertNotNull(observer)
    }
}