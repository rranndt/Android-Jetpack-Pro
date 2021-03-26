package com.kotlin.final.utils

import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.movies.detail.MoviesDetailItem
import com.kotlin.finalsub.data.source.remote.response.movies.genres.MovieGenres
import com.kotlin.finalsub.data.source.remote.response.movies.list.MoviesListItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.detail.TvSeriesDetailItem
import com.kotlin.finalsub.data.source.remote.response.tv.genres.TvSeriesGenres
import com.kotlin.finalsub.data.source.remote.response.tv.list.TvSeriesListItem

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object FakeDataDummy {

    /**
     * ****** *
     * Movies *
     * ****** *
     */
    fun getDummyMovies(): List<MoviesListItem> {
        val movies = ArrayList<MoviesListItem>()
        movies.add(
            MoviesListItem(
                "Wrong Turn",
                "/4U1SBHmwHkNA0eHZ2n1CuiC1K1g.jpg",
                873.7F,
                630586
            )
        )
        return movies
    }

    fun getDummyMoviesDetail(): MoviesEntity {
        return MoviesEntity(
            630586,
            "/4U1SBHmwHkNA0eHZ2n1CuiC1K1g.jpg",
            "/u5WUCO6irZoq27qbYYrtLUrCGDV.jpg",
            "Horror, Thriller",
            "Jen and a group of friends set out to hike the Appalachian Trail. Despite warnings to stick to the trail, the hikers stray off course—and cross into land inhabited by The Foundation, a hidden community of mountain dwellers who use deadly means to protect their way of life.",
            "2021-01-26",
            110,
            "Wrong Turn",
            873.7F,
            6.1F,
            false
        )
    }

    fun getDummyRemoteMoviesDetail(): MoviesDetailItem {
        return MoviesDetailItem(
            "Wrong Turn",
            "/u5WUCO6irZoq27qbYYrtLUrCGDV.jpg",
            genres = listOf(
                MovieGenres(
                    "Horror",
                    1
                ),
                MovieGenres(
                    "Thriller",
                    2
                )
            ),
            873.7F,
            630586,
            "Jen and a group of friends set out to hike the Appalachian Trail. Despite warnings to stick to the trail, the hikers stray off course—and cross into land inhabited by The Foundation, a hidden community of mountain dwellers who use deadly means to protect their way of life.",
            "Wrong Turn",
            110,
            "/4U1SBHmwHkNA0eHZ2n1CuiC1K1g.jpg",
            "2021-01-26",
            6.1F
        )
    }

    fun getDummyMoviesCast(): List<MoviesCastItem> = arrayListOf(
        MoviesCastItem(
            "Wrong Turn",
            "/4U1SBHmwHkNA0eHZ2n1CuiC1K1g.jpg",
            630586
        )
    )

    /**
     * ********* *
     * Tv Series *
     * ********* *
     */
    fun getDummyTvSeries(): List<TvSeriesListItem> {
        val tvSeries = ArrayList<TvSeriesListItem>()
        tvSeries.add(
            TvSeriesListItem(
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash",
                1407.5F,
                60735
            )
        )
        return tvSeries
    }

    fun getDummyTvSeriesDetail(): TvSeriesEntity {
        return TvSeriesEntity(
            60735,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
            "The Flash",
            "Drama, Sci-Fi, Fantasy",
            "2014-10-07",
            7.1F,
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            1407.5F,
            false
        )
    }

    fun getDummyRemoteTvSeriesDetail(): TvSeriesDetailItem {
        return TvSeriesDetailItem(
            "2014-10-07",
            "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
            genres = listOf(
                TvSeriesGenres(
                    "Drama",
                    1
                ),
                TvSeriesGenres(
                    "Sci-Fi",
                    2
                ),
                TvSeriesGenres(
                    "Fantasy",
                    3
                )
            ),
            1407.5F,
            60735,
            100,
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "The Flash",
            7.1F
        )
    }

    fun getDummyTvSeriesCast(): List<TvSeriesCastItem> = arrayListOf(
        TvSeriesCastItem(
            "The Flash",
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            60735
        )
    )

}