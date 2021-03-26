package com.kotlin.finalsub.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface CatalogueSource {

    /**
     * MOVIES
     */
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getMoviesDetail(moviesId: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesCast(moviesId: String): LiveData<List<MoviesCastItem>>

    fun setMoviesFavorite(movies: MoviesEntity, state: Boolean)

    fun getMoviesFavorite(sort: String): LiveData<PagedList<MoviesEntity>>

    /**
     * TV SERIES
     */
    fun getTvSeries(): LiveData<Resource<PagedList<TvSeriesEntity>>>

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<Resource<TvSeriesEntity>>

    fun getTvSeriesCast(tvSeriesId: String): LiveData<List<TvSeriesCastItem>>

    fun setTvSeriesFavorite(tvSeries: TvSeriesEntity, state: Boolean)

    fun getTvSeriesFavorite(sort: String): LiveData<PagedList<TvSeriesEntity>>

}