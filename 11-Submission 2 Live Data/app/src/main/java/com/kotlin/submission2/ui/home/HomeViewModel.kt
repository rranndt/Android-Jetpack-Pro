package com.kotlin.submission2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.submission2.data.repository.MoviesRepository
import com.kotlin.submission2.data.repository.response.TvSeriesListItem
import com.kotlin.submission2.data.repository.response.movies.MoviesCast
import com.kotlin.submission2.data.repository.response.movies.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.movies.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.TvSeriesDetailResponse

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movies: LiveData<List<MoviesListItem>> = moviesRepository.getMovies()
    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailResponse> =
        moviesRepository.getMoviesDetail(moviesId)

    val tvSeries: LiveData<List<TvSeriesListItem>> = moviesRepository.getTvSeries()
    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailResponse> =
        moviesRepository.getTvSeriesDetail(tvSeriesId)

}