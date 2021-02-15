package com.kotlin.submission2.network

import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastResponse
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListResponse
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesResponse
import com.kotlin.submission2.utils.Constant.MOVIES_CAST
import com.kotlin.submission2.utils.Constant.MOVIES_POPULAR
import com.kotlin.submission2.utils.Constant.MOVIES_WITH_ID
import com.kotlin.submission2.utils.Constant.STRING_API_KEY
import com.kotlin.submission2.utils.Constant.STRING_MOVIES_CAST_ID
import com.kotlin.submission2.utils.Constant.STRING_MOVIES_ID
import com.kotlin.submission2.utils.Constant.STRING_TV_SERIES_ID
import com.kotlin.submission2.utils.Constant.TV_SERIES_POPULAR
import com.kotlin.submission2.utils.Constant.TV_SERIES_WITH_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface ApiService {

    @GET(MOVIES_POPULAR)
    fun getMovies(
        @Query(STRING_API_KEY) apiKey: String
    ): Call<MoviesListResponse>

    @GET(MOVIES_WITH_ID)
    fun getMoviesDetail(
        @Path(STRING_MOVIES_ID) moviesId: String,
        @Query(STRING_API_KEY) apiKey: String
    ): Call<MoviesDetailItem>

    @GET(MOVIES_CAST)
    fun getMoviesCast(
        @Path(STRING_MOVIES_CAST_ID) moviesCastId: String,
        @Query(STRING_API_KEY) apiKey: String
    ): Call<MoviesCastResponse>

    @GET(TV_SERIES_POPULAR)
    fun getTvSeries(
        @Query(STRING_API_KEY) apiKey: String
    ): Call<TvSeriesResponse>

    @GET(TV_SERIES_WITH_ID)
    fun getTvSeriesDetail(
        @Path(STRING_TV_SERIES_ID) tvSeriesId: String,
        @Query(STRING_API_KEY) apiKey: String
    ): Call<TvSeriesDetailItem>

}