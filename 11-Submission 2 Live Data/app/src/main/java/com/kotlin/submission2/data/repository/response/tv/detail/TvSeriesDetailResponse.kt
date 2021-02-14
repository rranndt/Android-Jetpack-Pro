package com.kotlin.submission2.data.repository.response.tv.detail

import com.google.gson.annotations.SerializedName
import com.kotlin.submission2.data.repository.response.tv.genres.TvSeriesGenres

data class TvSeriesDetailResponse(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("genres")
    val genres: List<TvSeriesGenres>,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    )