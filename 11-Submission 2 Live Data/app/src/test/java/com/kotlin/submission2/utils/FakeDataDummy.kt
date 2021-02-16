package com.kotlin.submission2.utils

import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.genres.MovieGenres
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.genres.TvSeriesGenres
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object FakeDataDummy {

    private val listMoviesGenres: List<MovieGenres> = emptyList()
    private val listTvSeriesGenres: List<TvSeriesGenres> = emptyList()

    fun getFakeDataDummyMovies(): List<MoviesListItem> = arrayListOf(
        MoviesListItem(
            "",
            "",
            5f,
            1
        )
    )

    fun getFakeDataDummyMoviesCast(): List<MoviesCastItem> = arrayListOf(
        MoviesCastItem(
            "",
            "",
            1
        )
    )

    fun getFakeDataDummyMoviesDetail(): MoviesDetailItem =
        MoviesDetailItem(
            "",
            "",
            "",
            listMoviesGenres,
            100f,
            1,
            50,
            "",
            "",
            120,
            "",
            "",
            5f

        )

    fun getFakeDataDummyTvSeries(): List<TvSeriesListItem> = arrayListOf(
        TvSeriesListItem(
            "",
            "",
            5f,
            1
        )
    )

    fun getFakeDataDummyTvSeriesCast(): List<TvSeriesCastItem> = arrayListOf(
        TvSeriesCastItem(
            "",
            "",
            1
        )
    )

    fun getFakeDataDummyTvSeriesDetail(): TvSeriesDetailItem =
        TvSeriesDetailItem(
            "",
            "",
            listTvSeriesGenres,
            100f,
            1,
            50,
            "",
            "",
            "",
            5f
        )

}