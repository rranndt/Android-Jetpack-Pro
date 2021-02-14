package com.kotlin.submission2.data.source

import androidx.lifecycle.LiveData
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailResponse
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface DataSource {

    fun getMovies(): LiveData<List<MoviesListItem>>

    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailResponse>

    fun getTvSeries(): LiveData<List<TvSeriesListItem>>

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailResponse>

}