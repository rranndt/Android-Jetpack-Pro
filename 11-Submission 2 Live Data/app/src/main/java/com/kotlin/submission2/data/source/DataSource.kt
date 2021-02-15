package com.kotlin.submission2.data.source

import androidx.lifecycle.LiveData
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
interface DataSource {

    fun getMovies(): LiveData<List<MoviesListItem>>

    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailItem>

    fun getMoviesCast(moviesId: String): LiveData<List<MoviesCastItem>>

    fun getTvSeries(): LiveData<List<TvSeriesListItem>>

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailItem>

    fun getTvSeriesCast(tvSeriesId: String): LiveData<List<TvSeriesCastItem>>

}