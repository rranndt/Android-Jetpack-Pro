package com.kotlin.finalsub.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Dao
interface CatalogueDao {

    /**
     * MOVIES
     */
    @Query("SELECT * FROM movies_table ORDER BY voteAverage ASC")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movies_table WHERE id = :id")
    fun getMoviesById(id: String): LiveData<MoviesEntity>

    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMoviesFavorite(query: SupportSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovies(movies: MoviesEntity)

    /**
     * TV SERIES
     */
    @Query("SELECT * FROM tv_series_table ORDER BY voteAverage ASC")
    fun getTvSeries(): DataSource.Factory<Int, TvSeriesEntity>

    @Query("SELECT * FROM tv_series_table WHERE id = :id")
    fun getTvSeriesById(id: String): LiveData<TvSeriesEntity>

    @RawQuery(observedEntities = [TvSeriesEntity::class])
    fun getTvSeriesFavorite(query: SupportSQLiteQuery): DataSource.Factory<Int, TvSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvSeries(tvSeries: List<TvSeriesEntity>)

    @Update
    fun updateTvSeries(tvSeries: TvSeriesEntity)

}