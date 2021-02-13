package com.kotlin.submission2.data.source

import androidx.lifecycle.LiveData
import com.kotlin.submission2.data.repository.response.TvSeriesListItem
import com.kotlin.submission2.data.repository.response.movies.MoviesCast
import com.kotlin.submission2.data.repository.response.movies.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.movies.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.TvSeriesDetailResponse

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface MovieDataSource {

    fun getMovies(): LiveData<List<MoviesListItem>>

    fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailResponse>

    fun getTvSeries(): LiveData<List<TvSeriesListItem>>

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailResponse>

}