package com.kotlin.submission2.utils

import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.genres.MovieGenres
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.genres.TvSeriesGenres
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.utils.Constant.IMAGE_URL

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object FakeDataDummy {

    private val listMoviesGenres: List<MovieGenres> = emptyList()
    private val listTvSeriesGenres: List<TvSeriesGenres> = emptyList()

    fun getFakeDataDummyMovies(): List<MoviesListItem> = arrayListOf(
        MoviesListItem(
            "Wonder Woman 1984",
            "$IMAGE_URL/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            7f,
            464052
        )
    )

    fun getFakeDataDummyMoviesCast(): List<MoviesCastItem> = arrayListOf(
        MoviesCastItem(
            "Gal Gadot",
            "$IMAGE_URL/plLfB60M5cJrnog8KvAKhI4UJuk.jpg",
            90633
        )
    )

    fun getFakeDataDummyMoviesDetail(): MoviesDetailItem =
        MoviesDetailItem(
            "Wonder Woman 1984",
            "$IMAGE_URL/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            listMoviesGenres,
            2039.46f,
            464052,
            3642,
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "Wonder Woman 1984",
            152,
            "$IMAGE_URL/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020",
            6.9f

        )

    fun getFakeDataDummyTvSeries(): List<TvSeriesListItem> = arrayListOf(
        TvSeriesListItem(
            "$IMAGE_URL/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            "WandaVision",
            8.4f,
            85271
        )
    )

    fun getFakeDataDummyTvSeriesCast(): List<TvSeriesCastItem> = arrayListOf(
        TvSeriesCastItem(
            "Elizabeth Olsen",
            "$IMAGE_URL/wIU675y4dofIDVuhaNWPizJNtep.jpg",
            550843
        )
    )

    fun getFakeDataDummyTvSeriesDetail(): TvSeriesDetailItem =
        TvSeriesDetailItem(
            "2021",
            "$IMAGE_URL/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
            listTvSeriesGenres,
            100f,
            85271,
            8,
            "Disturbances on Halloween separate Wanda from Vision, who looks into anomalous activity in Westview.",
            "$IMAGE_URL/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            "WandaVision",
            8.4f
        )

}