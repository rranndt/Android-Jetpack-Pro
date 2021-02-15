package com.kotlin.submission2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.submission2.data.repository.DataRepository
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModel(private val moviesRepository: DataRepository) : ViewModel() {

    val movies: LiveData<List<MoviesListItem>> = moviesRepository.getMovies()
    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailItem> =
        moviesRepository.getMoviesDetail(moviesId)

    val tvSeries: LiveData<List<TvSeriesListItem>> = moviesRepository.getTvSeries()
    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailItem> =
        moviesRepository.getTvSeriesDetail(tvSeriesId)

    fun getCast(moviesId: String): LiveData<List<MoviesCastItem>> {
        return moviesRepository.getCast(moviesId)
    }
}