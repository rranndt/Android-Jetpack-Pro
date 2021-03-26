package com.kotlin.finalsub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.finalsub.data.Repository
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_MOVIES
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_TV_SERIES
import com.kotlin.finalsub.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var detailTvSeries: LiveData<Resource<TvSeriesEntity>>
    private lateinit var detailMovies: LiveData<Resource<MoviesEntity>>

    /**
     * Movies
     */
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> =
        repository.getMovies()

    fun getMoviesCast(moviesId: String): LiveData<List<MoviesCastItem>> =
        repository.getMoviesCast(moviesId)

    fun insertMovies() {
        val detailMovies = detailMovies.value
        if (detailMovies?.data != null) {
            val newState = !detailMovies.data.isFavorite
            repository.setMoviesFavorite(detailMovies.data, newState)
        }
    }

    fun getFavoriteMovies(sort: String) =
        repository.getMoviesFavorite(sort)

    /**
     * Tv Series
     */
    fun getTvSeries(): LiveData<Resource<PagedList<TvSeriesEntity>>> =
        repository.getTvSeries()

    fun getTvSeriesCast(tvSeriesId: String): LiveData<List<TvSeriesCastItem>> =
        repository.getTvSeriesCast(tvSeriesId)

    fun insertTvSeries() {
        val detailTvSeries = detailTvSeries.value
        if (detailTvSeries?.data != null) {
            val newState = !detailTvSeries.data.isFavorite
            repository.setTvSeriesFavorite(detailTvSeries.data, newState)
        }
    }

    fun getFavoriteTvSeries(sort: String) =
        repository.getTvSeriesFavorite(sort)

    /**
     * Detail
     */
    fun setCatalogue(id: String, category: String) {
        when (category) {
            BUNDLE_MOVIES -> {
                detailMovies = repository.getMoviesDetail(id)
            }
            BUNDLE_TV_SERIES -> {
                detailTvSeries = repository.getTvSeriesDetail(id)
            }
        }
    }

    fun getDetailMovies() = detailMovies

    fun getDetailTvSeries() = detailTvSeries

}