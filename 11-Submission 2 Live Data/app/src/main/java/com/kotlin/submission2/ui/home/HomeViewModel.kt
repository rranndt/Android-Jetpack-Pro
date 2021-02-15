package com.kotlin.submission2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.submission2.data.repository.DataRepository
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    // Movies
    val movies: LiveData<List<MoviesListItem>> = dataRepository.getMovies()

    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailItem> =
        dataRepository.getMoviesDetail(moviesId)

    fun getMoviesCast(moviesId: String): LiveData<List<MoviesCastItem>> =
        dataRepository.getMoviesCast(moviesId)


    // Tv Series
    val tvSeries: LiveData<List<TvSeriesListItem>> = dataRepository.getTvSeries()

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailItem> =
        dataRepository.getTvSeriesDetail(tvSeriesId)

    fun getTvSeriesCast(tvSeriesId: String): LiveData<List<TvSeriesCastItem>> =
        dataRepository.getTvSeriesCast(tvSeriesId)

}