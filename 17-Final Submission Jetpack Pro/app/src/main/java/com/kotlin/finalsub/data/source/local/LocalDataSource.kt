package com.kotlin.finalsub.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.local.room.CatalogueDao
import com.kotlin.finalsub.utils.SortUtils.getMoviesSortedQuery
import com.kotlin.finalsub.utils.SortUtils.getTvSeriesSortedQuery

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    /**
     * MOVIES
     */
    fun getMovies(): DataSource.Factory<Int, MoviesEntity> =
        catalogueDao.getMovies()

    fun getMoviesById(id: String): LiveData<MoviesEntity> =
        catalogueDao.getMoviesById(id)

    fun getMoviesFavorite(sort: String): DataSource.Factory<Int, MoviesEntity> =
        catalogueDao.getMoviesFavorite(getMoviesSortedQuery(sort))

    fun insertMovies(movies: List<MoviesEntity>) =
        catalogueDao.insertMovies(movies)

    fun updateMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        catalogueDao.updateMovies(movies)
    }

    /**
     * TV SERIES
     */
    fun getTvSeries(): DataSource.Factory<Int, TvSeriesEntity> =
        catalogueDao.getTvSeries()

    fun getTvSeriesById(id: String): LiveData<TvSeriesEntity> =
        catalogueDao.getTvSeriesById(id)

    fun getTvSeriesFavorite(sort: String): DataSource.Factory<Int, TvSeriesEntity> =
        catalogueDao.getTvSeriesFavorite(getTvSeriesSortedQuery(sort))

    fun insertTvSeries(tvSeries: List<TvSeriesEntity>) =
        catalogueDao.insertTvSeries(tvSeries)

    fun updateTvSeries(tvSeries: TvSeriesEntity, newState: Boolean) {
        tvSeries.isFavorite = newState
        catalogueDao.updateTvSeries(tvSeries)
    }

}