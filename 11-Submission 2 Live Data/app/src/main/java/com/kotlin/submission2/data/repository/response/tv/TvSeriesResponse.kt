package com.kotlin.submission2.data.repository.response

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(

    @field:SerializedName("results")
    val results: List<TvSeriesListItem>,

    )

data class TvSeriesListItem(


    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("id")
    val id: Int,

    )
