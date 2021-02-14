package com.kotlin.submission2.data.repository.response.tv.list

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(

    @field:SerializedName("results")
    val results: List<TvSeriesListItem>,

    )